package dcoms.mobile.itemLov;


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


import dcoms.mobile.whsLov.WarehouseBean;

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

public class ItemLovDC {
    
    String itemNumber;
    
    private static final String NOT_REACHABLE = "NotReachable"; // Indiates no network connectivity
    
    List s_itemList = new ArrayList();
    protected String itemNum;

    public void setItemNum(String itemNum) {
        String oldItemNum = this.itemNum;
        this.itemNum = itemNum;
        propertyChangeSupport.firePropertyChange("itemNum", oldItemNum, itemNum);
    }

    public String getItemNum() {
        return itemNum;
    }
    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
    private ProviderChangeSupport providerChangeSupport = new ProviderChangeSupport(this);

  

    public ItemBean[] getItems() {

        System.out.println("getItems" );
        ItemBean[] items = null;
        System.out.println("network reachable");
       // items = getItemsFromDB();
       items = (ItemBean[]) s_itemList.toArray(new ItemBean[s_itemList.size()]);

        return items;

    }
    public ItemLovDC() {
        super();
    }

    private ItemBean[] getItemsFromDB() {
    Connection conn = null;
   // List returnValue = new ArrayList();
   s_itemList.clear();

    try {
        itemNumber = getItemNum();
        conn = ConnectionFactory.getConnection();
        System.out.println("itemNumber: " + itemNumber);
        Statement stmt = conn.createStatement();
        String query = "SELECT ITEM_NUM, ITEM_DESC FROM ITEM WHERE ITEM_NUM LIKE '%"+itemNumber+"%'";
        System.out.println("query: " +query);
        
            ResultSet result = stmt.executeQuery(query);
        while (result.next()) {
        ItemBean ITEM = new ItemBean();
        Utility.ApplicationLogger.severe("Item Number: " + result.getString("ITEM_NUM"));
            System.out.println("Item Number: " + result.getString("ITEM_NUM"));
        ITEM.setItemNum(result.getString("ITEM_NUM"));
        ITEM.setDesc(result.getString("ITEM_DESC"));
    
        s_itemList.add(ITEM);
        System.out.println("Firing");
        providerChangeSupport.fireProviderRefresh("items");
    }

    } catch (Exception ex) {
    Utility.ApplicationLogger.severe(ex.getMessage());
    ex.printStackTrace();
    throw new RuntimeException(ex);
    }
    Collections.sort(s_itemList);
    return (ItemBean[])s_itemList.toArray(new ItemBean[s_itemList.size()]);
    }
    
    public void invokeWS() throws AdfInvocationException {
        System.out.println("InvokeWS");
        getItemsFromDB();

    }
    public void addProviderChangeListener(ProviderChangeListener l) {
        providerChangeSupport.addProviderChangeListener(l);
    }

    public void removeProviderChangeListener(ProviderChangeListener l) {
        providerChangeSupport.removeProviderChangeListener(l);
    }
}
