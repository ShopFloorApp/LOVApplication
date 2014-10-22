package dcoms.mobile.itemLov;

import dcoms.mobile.common.TransactionHeaderBean;

import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;

public class ItemResponseBean {
    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    public ItemResponseBean() {
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

    public void setItemListBean(ItemListBean itemListBean) {
        ItemListBean oldItemListBean = this.itemListBean;
        this.itemListBean = itemListBean;
        propertyChangeSupport.firePropertyChange("itemListBean", oldItemListBean, itemListBean);
    }

    public ItemListBean getItemListBean() {
        return itemListBean;
    }
    protected ItemListBean itemListBean;

    public void addPropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.addPropertyChangeListener(l);
    }

    public void removePropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.removePropertyChangeListener(l);
    }
}
