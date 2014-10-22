package dcoms.mobile.common;

import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;

public class AttributeListBean {
    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    public AttributeListBean() {
        super();
    }
    protected AttributeBean Attr;

    public void setAttr(AttributeBean Attr) {
        AttributeBean oldAttr = this.Attr;
        this.Attr = Attr;
        propertyChangeSupport.firePropertyChange("Attr", oldAttr, Attr);
    }

    public AttributeBean getAttr() {
        return Attr;
    }

    public void addPropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.addPropertyChangeListener(l);
    }

    public void removePropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.removePropertyChangeListener(l);
    }
}
