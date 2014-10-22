package dcoms.mobile.whsLov;




import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;

public class WarehouseBean implements Comparable{
    
    private String org_code;

    public void setOrg_code(String org_code) {
        String oldOrg_code = this.org_code;
        this.org_code = org_code;
        propertyChangeSupport.firePropertyChange("org_code", oldOrg_code, org_code);
    }

    public String getOrg_code() {
        return org_code;
    }

    public void setOrg_name(String org_name) {
        String oldOrg_name = this.org_name;
        this.org_name = org_name;
        propertyChangeSupport.firePropertyChange("org_name", oldOrg_name, org_name);
    }

    public String getOrg_name() {
        return org_name;
    }

    public void setAddress(Integer address) {
        Integer oldAddress = this.address;
        this.address = address;
        propertyChangeSupport.firePropertyChange("address", oldAddress, address);
    }

    public Integer getAddress() {
        return address;
    }

    public void setPropertyChangeSupport(PropertyChangeSupport propertyChangeSupport) {
        PropertyChangeSupport oldPropertyChangeSupport = this.propertyChangeSupport;
        this.propertyChangeSupport = propertyChangeSupport;
        propertyChangeSupport.firePropertyChange("propertyChangeSupport", oldPropertyChangeSupport,
                                                 propertyChangeSupport);
    }

    public PropertyChangeSupport getPropertyChangeSupport() {
        return propertyChangeSupport;
    }
    private String org_name;
    private Integer address;
    
    private transient PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    public WarehouseBean() {
        super();
    }

    public boolean equals(Object object) {
    if (this == object) {
    return true;
    }
    if (!(object instanceof WarehouseBean)) {
    return false;
    }
    final WarehouseBean other = (WarehouseBean)object;
    if (!(org_code == null ? other.org_code == null : org_code.equals(other.org_code))) {
    return false;
    }
    return true;
    }

    public int hashCode() {
    final int PRIME = 37;
    int result = 1;
    result = PRIME * result + ((org_code == null) ? 0 : org_code.hashCode());
    return result;
    }

    public int compareTo(Object o) {
    if (this.equals(o)){
    return 0;
    }
    else if (o instanceof WarehouseBean){
    return this.org_name.compareTo(((WarehouseBean)o).getOrg_name());
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
