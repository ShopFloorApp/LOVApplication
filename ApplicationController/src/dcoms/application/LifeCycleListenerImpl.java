package dcoms.application;

import SQLite.JDBCDataSource;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;

import java.io.InputStreamReader;

import java.sql.Connection;

import java.sql.Statement;

import oracle.adfmf.application.LifeCycleListener;
import oracle.adfmf.framework.api.AdfmfJavaUtilities;
import oracle.adfmf.util.Utility;


/**
 * The application life cycle listener provides the basic structure for developers needing
 * to include their own functionality during the different stages of the application life
 * cycle.  Please note that there is <b>no user</b> associated with any of the following
 * methods.
 *
 * Common examples of functionality that might be added:
 *
 * start:
 *   1. determine if the application has updates
 *   2. determine if there already exists a local application database image
 *   3. setup any one time state for the application
 *
 * activate:
 *   1. read any application cache stores and re-populate state (if needed)
 *   2. establish/re-establish any database connections and cursors
 *   3. process any pending web-service requests
 *   4. obtain any required resources
 *
 * deactivate:
 *   1. write any restorable state to an application cache store
 *   2. close any database cursors and connections
 *   3. defer any pending web-service requests
 *   4. release held resources
 *
 * stop:
 *   1. logoff any remote services
 *
 * NOTE:
 * 1. In order for the system to recognize an application life cycle listener
 *    it must be registered in the maf-application.xml file.
 * 2. Application assemblers must implement this interface if they would like to
 *    receive notification of application start, hibernation, and application resume.
 *    If a secure web service is needed, you will need to do this from your 'default'
 *    feature where you will have an associated user.
 *
 * @see oracle.adfmf.application.LifeCycleListener
 */
public class LifeCycleListenerImpl implements LifeCycleListener
{
  public LifeCycleListenerImpl()
  {
  }

  /**
   * The start method will be called at the start of the application.
   * 
   * NOTE:
   * 1. This is a <b>blocking</b> call and will freeze the user interface
   *    while the method is being executed.  If you have any longer running
   *    items you should create a background thread and do the work there.
   * 2. Only the application controller's classes will be available in this
   *    method.
   * 3. At this stage, only an anonymous user is associated with the application
   *    so do not attempt to call any secure web services in this method.
   */
  public void start()
  {
      try {
          System.out.println("Inside start");
          initializeDatabaseFromScript();
      } catch (Exception ex) {
          Utility.ApplicationLogger.severe(ex.getMessage());
          throw new RuntimeException(ex);
      }
  }

  /**
   * The stop method will be called at the termination of the application.
   * 
   * NOTE:
   * 1. Depending on how the application is being shutdown, this method may
   *    or may not be called. For example, if a user kills the Application from
   *    the iOS multitask UI then stop will not be called.  Because of this, each
   *    feature should save off their state in the deactivate handler.
   * 2. Only the application controller's classes will be available in this
   *    method.
   * 3. At this stage, only an anonymous user is associated with the application
   *    so do not attempt to call any secure web services in this method.
   */
  public void stop()
  {
    // Add code here...
  }

  /**
   * The activate method will be called when the application is started (post
   * the start method) and when an application is resumed by the operating
   * system.  If the application supports checkpointing, this is a place where
   * the application should read the checkpoint information and resume the process.
   * 
   * NOTE:
   * 1. This is a <b>blocking</b> call and will freeze the user interface
   *    while the method is being executed.  If you have any longer running
   *    items you should create a background thread and do the work there.
   * 2. Only the application controller's classes will be available in this
   *    method.
   * 3. At this stage, only an anonymous user is associated with the application
   *    so do not attempt to call any secure web services in this method.
   * 4. Once an application is activated, the visible feature's activate lifecycle
   *    method will be executed (if configured) post this method being called.
   */
  public void activate()
  {
    // Add code here...
  }

  /**
   * The deactivate method will be called as part of the application shutdown
   * process or when the application is being deactivated/hibernated by the
   * operating system.  This is the place where application developers would
   * write application checkpoint information in either a database or a "device
   * only" file so if the application is terminated while in the background
   * the application can resume the process when the application is reactivated.
   * 
   * NOTE:
   * 1. This is a <b>blocking</b> call and will freeze the user interface
   *    while the method is being executed.  If you have any longer running
   *    items you should create a background thread and do the work there.
   * 2. Only the application controller's classes will be available in this
   *    method.
   * 3. At this stage, only an anonymous user is associated with the application
   *    so do not attempt to call any secure web services in this method.
   * 4. When an application is being deactivated, the visible feature's
   *    deactivate lifecycle method will be executed (if configured) prior to
   *    this method being called.
   */
  public void deactivate()
  {
    // Add code here...
  }
  
    // 1: Read sql script from device storage one line at a time to create database records
            private void initializeDatabaseFromScript() throws Exception {
                
                System.out.println("Inside initializeDatabaseFromScript");
                InputStream scriptStream = null;
                Connection conn = null;
                try {
                    
        // 2: Retrieve the path to the local database file           
                    Utility.ApplicationLogger.info("Initializing DB...");
                    String docRoot = AdfmfJavaUtilities.getDirectoryPathRoot(AdfmfJavaUtilities.ApplicationDirectory);
                    String dbName = docRoot + "/sm.db";
                    System.out.println("File is "+dbName);
        // 3: Check if the db file exists or not
                    File dbFile = new File(dbName);
                    if (dbFile.exists())
                        return;
                    
        // 4: If it doesn't exist then we create it - make sure to turn off autocommit - db file created when opened, if it doesn't exist
                    conn = new JDBCDataSource("jdbc:sqlite:" + dbName).getConnection();
                    conn.setAutoCommit(false);
                    
        //  5: Retrieve the db script from storage and store it in memory
                    Utility.ApplicationLogger.info("Reading script");
                    System.out.println("Reading Script");
                    scriptStream =
                        Thread.currentThread().getContextClassLoader().getResourceAsStream("META-INF/initializeDB.sql");
                    BufferedReader scriptReader = new BufferedReader(new InputStreamReader(scriptStream));
                    String nextLine;
                    StringBuffer nextStatement = new StringBuffer();
                    System.out.println("AFter next line");
        // 6: Iterate line after line in the script - if line starts with rem or comment, then skip it.  Otherwise execute each line
                    Statement stmt = conn.createStatement();
                    while ((nextLine = scriptReader.readLine()) != null) {
                    if (nextLine.startsWith("REM") || nextLine.startsWith("COMMIT") || nextLine.length() <1)
                        continue;
                    nextStatement.append(nextLine);
                        if (nextLine.endsWith(";")) {
                            System.out.println("Execute statement"+nextStatement.toString());
                            Utility.ApplicationLogger.info("Execute statement: " + nextStatement.toString());
                            stmt.execute(nextStatement.toString());
                            nextStatement = new StringBuffer();
                        }
                    }
                    
        // 7: Exception handeling
                        }finally {
                            if (conn != null) { 
                                conn.commit();
                                conn.close();
                        }
                        }
            } 

}
