package dcoms.mobile.serialLov;

import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;

public class SerialListBean {
    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    public SerialListBean() {
        super();
    }
    protected SerialBean Serial;

    public void setSerial(SerialBean Serial) {
        SerialBean oldSerial = this.Serial;
        this.Serial = Serial;
        propertyChangeSupport.firePropertyChange("Serial", oldSerial, Serial);
    }

    public SerialBean getSerial() {
        return Serial;
    }

    public void addPropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.addPropertyChangeListener(l);
    }

    public void removePropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.removePropertyChangeListener(l);
    }
}
