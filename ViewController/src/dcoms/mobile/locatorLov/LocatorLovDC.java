package dcoms.mobile.locatorLov;

import dcoms.mobile.common.StatusBean;
import dcoms.mobile.common.TransactionHeaderBean;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import database.ConnectionFactory;


import dcoms.mobile.subinvLov.SubinvBean;

import java.util.Collections;

import javax.el.ValueExpression;

import oracle.adfmf.framework.api.AdfmfJavaUtilities;
import oracle.adfmf.framework.api.GenericTypeBeanSerializationHelper;
import oracle.adfmf.framework.exception.AdfException;
import oracle.adfmf.framework.exception.AdfInvocationException;
import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;
import oracle.adfmf.java.beans.ProviderChangeListener;
import oracle.adfmf.java.beans.ProviderChangeSupport;
import oracle.adfmf.util.GenericType;
import oracle.adfmf.util.GenericVirtualType;
import oracle.adfmf.util.Utility;

public class LocatorLovDC {
    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    public LocatorLovDC() {
        super();
    }

    String loc_name;
    private static final String NOT_REACHABLE = "NotReachable"; // Indiates no network connectivity

    // List orgs = new ArrayList();
    List s_locList = new ArrayList();
    protected String locator_name;

    public void setLocator_name(String locator_name) {
        String oldLocator_name = this.locator_name;
        this.locator_name = locator_name;
        propertyChangeSupport.firePropertyChange("locator_name", oldLocator_name, locator_name);
    }

    public String getLocator_name() {
        return locator_name;
    }
    private ProviderChangeSupport providerChangeSupport = new ProviderChangeSupport(this);
       // private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    public void addPropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.addPropertyChangeListener(l);
    }

    public void removePropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.removePropertyChangeListener(l);
    }

    public LocatorBean[] getLoc() {

        System.out.println("getLocator");
        //   s_locList.clear();
        //SubinvBean[] orgs = null;
        System.out.println("network reachable");
        //orgs =
       // getLocNameFromDB();
        System.out.println("array size" + s_locList.size());

        LocatorBean[] loc = (LocatorBean[]) s_locList.toArray(new LocatorBean[s_locList.size()]);
        return loc;

    }

    private void getLocNameFromDB() {
        Connection conn = null;
        s_locList.clear();

        try {
            loc_name = getLocator_name();
            conn = ConnectionFactory.getConnection();
            System.out.println("loc_name: " + loc_name);
            Statement stmt = conn.createStatement();
            String query =
                "SELECT locator_name,locator_desc FROM LOCATORS WHERE locator_name LIKE '%" + loc_name + "%'";
            System.out.println("query: " + query);

            ResultSet result = stmt.executeQuery(query);
            while (result.next()) {
                LocatorBean locNames = new LocatorBean();
                Utility.ApplicationLogger.severe("locator_name: " + result.getString("LOCATOR_NAME"));
                System.out.println("Locator_name: " + result.getString("LOCATOR_NAME"));
                locNames.setLocator_name(result.getString("LOCATOR_NAME"));
                locNames.setLocator_desc(result.getString("LOCATOR_DESC"));

                s_locList.add(locNames);
                System.out.println("Firing");
                providerChangeSupport.fireProviderRefresh("loc");

            }

        } catch (Exception ex) {
            Utility.ApplicationLogger.severe(ex.getMessage());
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        Collections.sort(s_locList);
        //return (SubinvBean[])returnValue.toArray(new SubinvBean[returnValue.size()]);
    }

    public void invokeWS() throws AdfInvocationException {
        System.out.println("InvokeWS");
        getLocNameFromDB();

    }
    public void addProviderChangeListener(ProviderChangeListener l) {
        providerChangeSupport.addProviderChangeListener(l);
    }

    public void removeProviderChangeListener(ProviderChangeListener l) {
        providerChangeSupport.removeProviderChangeListener(l);
    }

    

}
