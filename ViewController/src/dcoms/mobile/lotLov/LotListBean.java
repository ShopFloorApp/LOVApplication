package dcoms.mobile.lotLov;

import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;

public class LotListBean {
    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    public LotListBean() {
        super();
    }
    protected LotBean Lot;

    public void setLot(LotBean Lot) {
        LotBean oldLot = this.Lot;
        this.Lot = Lot;
        propertyChangeSupport.firePropertyChange("Lot", oldLot, Lot);
    }

    public LotBean getLot() {
        return Lot;
    }

    public void addPropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.addPropertyChangeListener(l);
    }

    public void removePropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.removePropertyChangeListener(l);
    }
}
