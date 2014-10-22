package dcoms.mobile.locatorLov;




import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;

public class LocatorBean implements Comparable{
    
    protected String locator_name;
    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    public void setLocator_name(String locator_name) {
        String oldLocator_name = this.locator_name;
        this.locator_name = locator_name;
        propertyChangeSupport.firePropertyChange("locator_name", oldLocator_name, locator_name);
    }

    public String getLocator_name() {
        return locator_name;
    }

    public void setLocator_desc(String locator_desc) {
        String oldLocator_desc = this.locator_desc;
        this.locator_desc = locator_desc;
        propertyChangeSupport.firePropertyChange("locator_desc", oldLocator_desc, locator_desc);
    }

    public String getLocator_desc() {
        return locator_desc;
    }
    protected String locator_desc;
    
    public LocatorBean() {
        super();
    }

    public boolean equals(Object object) {
    if (this == object) {
    return true;
    }
    if (!(object instanceof LocatorBean)) {
    return false;
    }
    final LocatorBean other = (LocatorBean)object;
    if (!(locator_name == null ? other.locator_name == null : locator_name.equals(other.locator_name))) {
    return false;
    }
    return true;
    }

    public int hashCode() {
    final int PRIME = 37;
    int result = 1;
    result = PRIME * result + ((locator_name == null) ? 0 : locator_name.hashCode());
    return result;
    }

    public int compareTo(Object o) {
    if (this.equals(o)){
    return 0;
    }
    else if (o instanceof LocatorBean){
    return this.locator_desc.compareTo(((LocatorBean)o).getLocator_desc());
    }
    else{
    throw new ClassCastException("orgName expected.");
    }
    }



    public void addPropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.addPropertyChangeListener(l);
    }

    public void removePropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.removePropertyChangeListener(l);
    }
}
