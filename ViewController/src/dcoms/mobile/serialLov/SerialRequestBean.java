package dcoms.mobile.serialLov;

import dcoms.mobile.common.AttributeListBean;
import dcoms.mobile.common.TransactionHeaderBean;

import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;

public class SerialRequestBean {
    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    public SerialRequestBean() {
        super();
    }
    protected TransactionHeaderBean TransactionHeader;
    protected String Whse;
    protected String Item;
    protected String SerialNum;
    protected String SerialCount;
    protected String Subinv;
    protected String Locator;
    protected String Action;
    protected AttributeListBean Attributes;

    public void setTransactionHeader(TransactionHeaderBean TransactionHeader) {
        TransactionHeaderBean oldTransactionHeader = this.TransactionHeader;
        this.TransactionHeader = TransactionHeader;
        propertyChangeSupport.firePropertyChange("TransactionHeader", oldTransactionHeader, TransactionHeader);
    }

    public TransactionHeaderBean getTransactionHeader() {
        return TransactionHeader;
    }

    public void setWhse(String Whse) {
        String oldWhse = this.Whse;
        this.Whse = Whse;
        propertyChangeSupport.firePropertyChange("Whse", oldWhse, Whse);
    }

    public String getWhse() {
        return Whse;
    }

    public void setItem(String Item) {
        String oldItem = this.Item;
        this.Item = Item;
        propertyChangeSupport.firePropertyChange("Item", oldItem, Item);
    }

    public String getItem() {
        return Item;
    }

    public void setSerialNum(String SerialNum) {
        String oldSerialNum = this.SerialNum;
        this.SerialNum = SerialNum;
        propertyChangeSupport.firePropertyChange("SerialNum", oldSerialNum, SerialNum);
    }

    public String getSerialNum() {
        return SerialNum;
    }

    public void setSerialCount(String SerialCount) {
        String oldSerialCount = this.SerialCount;
        this.SerialCount = SerialCount;
        propertyChangeSupport.firePropertyChange("SerialCount", oldSerialCount, SerialCount);
    }

    public String getSerialCount() {
        return SerialCount;
    }

    public void setSubinv(String Subinv) {
        String oldSubinv = this.Subinv;
        this.Subinv = Subinv;
        propertyChangeSupport.firePropertyChange("Subinv", oldSubinv, Subinv);
    }

    public String getSubinv() {
        return Subinv;
    }

    public void setLocator(String Locator) {
        String oldLocator = this.Locator;
        this.Locator = Locator;
        propertyChangeSupport.firePropertyChange("Locator", oldLocator, Locator);
    }

    public String getLocator() {
        return Locator;
    }

    public void setAction(String Action) {
        String oldAction = this.Action;
        this.Action = Action;
        propertyChangeSupport.firePropertyChange("Action", oldAction, Action);
    }

    public String getAction() {
        return Action;
    }

    public void setAttributes(AttributeListBean Attributes) {
        AttributeListBean oldAttributes = this.Attributes;
        this.Attributes = Attributes;
        propertyChangeSupport.firePropertyChange("Attributes", oldAttributes, Attributes);
    }

    public AttributeListBean getAttributes() {
        return Attributes;
    }

    public void addPropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.addPropertyChangeListener(l);
    }

    public void removePropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.removePropertyChangeListener(l);
    }
}

