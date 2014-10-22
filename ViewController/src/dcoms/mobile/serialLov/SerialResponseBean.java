package dcoms.mobile.serialLov;

import dcoms.mobile.common.TransactionHeaderBean;

import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;

public class SerialResponseBean {
    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    public SerialResponseBean() {
        super();
    }
    protected TransactionHeaderBean TransactionHeader;
    protected SerialListBean SerialDetails;

    public void setTransactionHeader(TransactionHeaderBean TransactionHeader) {
        TransactionHeaderBean oldTransactionHeader = this.TransactionHeader;
        this.TransactionHeader = TransactionHeader;
        propertyChangeSupport.firePropertyChange("TransactionHeader", oldTransactionHeader, TransactionHeader);
    }

    public TransactionHeaderBean getTransactionHeader() {
        return TransactionHeader;
    }

    public void setSerialDetails(SerialListBean SerialDetails) {
        SerialListBean oldSerialDetails = this.SerialDetails;
        this.SerialDetails = SerialDetails;
        propertyChangeSupport.firePropertyChange("SerialDetails", oldSerialDetails, SerialDetails);
    }

    public SerialListBean getSerialDetails() {
        return SerialDetails;
    }

    public void addPropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.addPropertyChangeListener(l);
    }

    public void removePropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.removePropertyChangeListener(l);
    }
}
