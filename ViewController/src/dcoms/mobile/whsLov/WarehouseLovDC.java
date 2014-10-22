package dcoms.mobile.whsLov;


import dcoms.mobile.common.StatusBean;
import dcoms.mobile.common.TransactionHeaderBean;

import dcoms.mobile.itemLov.ItemBean;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import database.ConnectionFactory;


import dcoms.mobile.serialLov.SerialBean;

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

public class WarehouseLovDC {

    String orgCode;

    private static final String NOT_REACHABLE = "NotReachable"; // Indiates no network connectivity

    // List orgs = new ArrayList();
    List s_wshList = new ArrayList();
    protected String org_code;

    public void setOrg_code(String org_code) {
        String oldOrg_code = this.org_code;
        this.org_code = org_code;
        propertyChangeSupport.firePropertyChange("org_code", oldOrg_code, org_code);
    }

    public String getOrg_code() {
        return org_code;
    }


    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
    private ProviderChangeSupport providerChangeSupport = new ProviderChangeSupport(this);


    public WarehouseBean[] getOrgs() {
        System.out.println("array size" + s_wshList.size());
        WarehouseBean[] orgs = (WarehouseBean[]) s_wshList.toArray(new WarehouseBean[s_wshList.size()]);
        return orgs;
    }

    public WarehouseLovDC() {
        super();
    }

    private void getOrgsFromDB() {
        Connection conn = null;
        s_wshList.clear();
        try {
            orgCode = getOrg_code();
            conn = ConnectionFactory.getConnection();
            System.out.println("orgCode: " + orgCode);
            Statement stmt = conn.createStatement();
            String query = "SELECT org_code, org_name FROM WAREHOUSE_DETAILS WHERE org_code LIKE '%" + orgCode + "%'";
            System.out.println("query: " + query);

            ResultSet result = stmt.executeQuery(query);
            while (result.next()) {
                WarehouseBean ORGS = new WarehouseBean();
                Utility.ApplicationLogger.severe("Item Number: " + result.getString("ORG_CODE"));
                System.out.println("Item Number: " + result.getString("ORG_CODE"));
                ORGS.setOrg_code(result.getString("ORG_CODE"));
                ORGS.setOrg_name(result.getString("ORG_NAME"));
                s_wshList.add(ORGS);
                System.out.println("Firing");
                providerChangeSupport.fireProviderRefresh("orgs");
            }

        } catch (Exception ex) {
            Utility.ApplicationLogger.severe(ex.getMessage());
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        Collections.sort(s_wshList);
        //return (WarehouseBean[])returnValue.toArray(new WarehouseBean[returnValue.size()]);
    }

    public void invokeWS() throws AdfInvocationException {
        System.out.println("InvokeWS");
        getOrgsFromDB();

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
