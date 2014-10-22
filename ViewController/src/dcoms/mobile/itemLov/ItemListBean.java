package dcoms.mobile.itemLov;

import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;

public class ItemListBean {
    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    public ItemListBean() {
        super();
    }
    
    protected ItemBean itemBean;

    public void setItemBean(ItemBean itemBean) {
        ItemBean oldItemBean = this.itemBean;
        this.itemBean = itemBean;
        propertyChangeSupport.firePropertyChange("itemBean", oldItemBean, itemBean);
    }

    public ItemBean getItemBean() {
        return itemBean;
    }

    public void addPropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.addPropertyChangeListener(l);
    }

    public void removePropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.removePropertyChangeListener(l);
    }
}
