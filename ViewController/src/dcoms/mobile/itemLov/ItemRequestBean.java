package dcoms.mobile.itemLov;

import dcoms.mobile.common.AttributeListBean;
import dcoms.mobile.common.TransactionHeaderBean;

import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;

public class ItemRequestBean {
    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    public ItemRequestBean() {
        super();
    }
    
    protected TransactionHeaderBean TxnHdr;

    public void setTxnHdr(TransactionHeaderBean TxnHdr) {
        TransactionHeaderBean oldTxnHdr = this.TxnHdr;
        this.TxnHdr = TxnHdr;
        propertyChangeSupport.firePropertyChange("TxnHdr", oldTxnHdr, TxnHdr);
    }

    public TransactionHeaderBean getTxnHdr() {
        return TxnHdr;
    }

    public void setOrganizationId(String OrganizationId) {
        String oldOrganizationId = this.OrganizationId;
        this.OrganizationId = OrganizationId;
        propertyChangeSupport.firePropertyChange("OrganizationId", oldOrganizationId, OrganizationId);
    }

    public String getOrganizationId() {
        return OrganizationId;
    }

    public void setItemNum(String ItemNum) {
        String oldItemNum = this.ItemNum;
        this.ItemNum = ItemNum;
        propertyChangeSupport.firePropertyChange("ItemNum", oldItemNum, ItemNum);
    }

    public String getItemNum() {
        return ItemNum;
    }

    public void setDesc(String Desc) {
        String oldDesc = this.Desc;
        this.Desc = Desc;
        propertyChangeSupport.firePropertyChange("Desc", oldDesc, Desc);
    }

    public String getDesc() {
        return Desc;
    }

    public void setAttributes(AttributeListBean Attributes) {
        AttributeListBean oldAttributes = this.Attributes;
        this.Attributes = Attributes;
        propertyChangeSupport.firePropertyChange("Attributes", oldAttributes, Attributes);
    }

    public AttributeListBean getAttributes() {
        return Attributes;
    }
    protected String OrganizationId;
    protected String ItemNum;
    protected String Desc;
    protected AttributeListBean Attributes;


    public void addPropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.addPropertyChangeListener(l);
    }

    public void removePropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.removePropertyChangeListener(l);
    }
}
