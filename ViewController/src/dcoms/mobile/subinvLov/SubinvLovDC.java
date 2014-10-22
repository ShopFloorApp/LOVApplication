package dcoms.mobile.subinvLov;



import dcoms.mobile.common.StatusBean;
import dcoms.mobile.common.TransactionHeaderBean;



import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import database.ConnectionFactory;


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

public class SubinvLovDC {
    
    String subinv_code;
    
    private static final String NOT_REACHABLE = "NotReachable"; // Indiates no network connectivity
    
   // List orgs = new ArrayList();
    List s_subinvList = new ArrayList();
    protected String subinvCode;
    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    public void setSubinvCode(String subinvCode) {
        String oldSubinvCode = this.subinvCode;
        this.subinvCode = subinvCode;
        propertyChangeSupport.firePropertyChange("subinvCode", oldSubinvCode, subinvCode);
    }

    public String getSubinvCode() {
        return subinvCode;
    }
    private ProviderChangeSupport providerChangeSupport = new ProviderChangeSupport(this);

    public SubinvBean[] getSubinv() {

        System.out.println("getSubInv" );
        //SubinvBean[] orgs = null;
        System.out.println("network reachable");
        //orgs = 
     //   getSubinvFromDB();
        System.out.println("array size" +s_subinvList.size());
        
        SubinvBean[] subinv = (SubinvBean[]) s_subinvList.toArray(new SubinvBean[s_subinvList.size()]);
        return subinv;

    }
    public SubinvLovDC() {
        super();
    }

    private void getSubinvFromDB() {
    Connection conn = null;
        s_subinvList.clear();

    try {
        subinv_code = getSubinvCode();
        conn = ConnectionFactory.getConnection();
        System.out.println("subinv_code: " + subinv_code);
        Statement stmt = conn.createStatement();
        String query = "SELECT subinv_code, subinv_desc, subinv_desc FROM SUBINV WHERE subinv_code LIKE '%"+subinv_code+"%'";
        System.out.println("query: " +query);
        
            ResultSet result = stmt.executeQuery(query);
        while (result.next()) {
        SubinvBean subInvCodes = new SubinvBean();
        Utility.ApplicationLogger.severe("SubInv code: " + result.getString("SUBINV_CODE"));
            System.out.println("SubInv code: " + result.getString("SUBINV_CODE"));
        subInvCodes.setSubinv_code(result.getString("SUBINV_CODE"));
        subInvCodes.setSubinv_desc(result.getString("SUBINV_DESC"));

  s_subinvList.add(subInvCodes);
            System.out.println("Array Size "+s_subinvList.size());
        System.out.println("Firing");
        providerChangeSupport.fireProviderRefresh("subinv");
    }

    } catch (Exception ex) {
    Utility.ApplicationLogger.severe(ex.getMessage());
    ex.printStackTrace();
    throw new RuntimeException(ex);
    }
    Collections.sort(s_subinvList);
   //return (SubinvBean[])returnValue.toArray(new SubinvBean[returnValue.size()]);
    }
    
    public void invokeWS() throws AdfInvocationException {
        System.out.println("InvokeWS");
        getSubinvFromDB();

    }

    public void addPropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.addPropertyChangeListener(l);
    }

    public void removePropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.removePropertyChangeListener(l);
    }
    public void addProviderChangeListener(ProviderChangeListener l) {
        providerChangeSupport.addProviderChangeListener(l);
    }

    public void removeProviderChangeListener(ProviderChangeListener l) {
        providerChangeSupport.removeProviderChangeListener(l);
    }
}
