package dcoms.mobile.common;

import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;

public class StatusBean {
    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    public StatusBean() {
        super();
    }

    public void setCode(String Code) {
        String oldCode = this.Code;
        this.Code = Code;
        propertyChangeSupport.firePropertyChange("Code", oldCode, Code);
    }

    public String getCode() {
        return Code;
    }

    public void setMsg(String Msg) {
        String oldMsg = this.Msg;
        this.Msg = Msg;
        propertyChangeSupport.firePropertyChange("Msg", oldMsg, Msg);
    }

    public String getMsg() {
        return Msg;
    }
    protected String Code;
    protected String Msg;

    public void addPropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.addPropertyChangeListener(l);
    }

    public void removePropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.removePropertyChangeListener(l);
    }
}
