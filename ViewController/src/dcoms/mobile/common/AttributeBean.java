package dcoms.mobile.common;

import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;

public class AttributeBean {
    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    public AttributeBean() {
        super();
    }

    public void setN(String N) {
        String oldN = this.N;
        this.N = N;
        propertyChangeSupport.firePropertyChange("N", oldN, N);
    }

    public String getN() {
        return N;
    }

    public void setV(String V) {
        String oldV = this.V;
        this.V = V;
        propertyChangeSupport.firePropertyChange("V", oldV, V);
    }

    public String getV() {
        return V;
    }
    protected String N;
    protected String V;

    public void addPropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.addPropertyChangeListener(l);
    }

    public void removePropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.removePropertyChangeListener(l);
    }
}
