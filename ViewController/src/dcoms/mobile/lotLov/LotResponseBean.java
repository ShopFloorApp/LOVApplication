package dcoms.mobile.lotLov;


import dcoms.mobile.common.TransactionHeaderBean;
import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;

public class LotResponseBean {
    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    public LotResponseBean() {
        super();
    }
    protected TransactionHeaderBean TransactionHeader;
    protected LotListBean LotDetails;

    public void setLotDetails(LotListBean LotDetails) {
        LotListBean oldLotDetails = this.LotDetails;
        this.LotDetails = LotDetails;
        propertyChangeSupport.firePropertyChange("LotDetails", oldLotDetails, LotDetails);
    }

    public LotListBean getLotDetails() {
        return LotDetails;
    }


    public void setTransactionHeader(TransactionHeaderBean TransactionHeader) {
        TransactionHeaderBean oldTransactionHeader = this.TransactionHeader;
        this.TransactionHeader = TransactionHeader;
        propertyChangeSupport.firePropertyChange("TransactionHeader", oldTransactionHeader, TransactionHeader);
    }

    public TransactionHeaderBean getTransactionHeader() {
        return TransactionHeader;
    }

    public void addPropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.addPropertyChangeListener(l);
    }

    public void removePropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.removePropertyChangeListener(l);
    }
}
