package dcoms.mobile.subinvLov;




import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;

public class SubinvBean implements Comparable{
    
    protected String subinv_code;
    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    public void setSubinv_code(String subinv_code) {
        String oldSubinv_code = this.subinv_code;
        this.subinv_code = subinv_code;
        propertyChangeSupport.firePropertyChange("subinv_code", oldSubinv_code, subinv_code);
    }

    public String getSubinv_code() {
        return subinv_code;
    }

    public void setSubinv_desc(String subinv_desc) {
        String oldSubinv_desc = this.subinv_desc;
        this.subinv_desc = subinv_desc;
        propertyChangeSupport.firePropertyChange("subinv_desc", oldSubinv_desc, subinv_desc);
    }

    public String getSubinv_desc() {
        return subinv_desc;
    }

    public void setOrg_id(Integer org_id) {
        Integer oldOrg_id = this.org_id;
        this.org_id = org_id;
        propertyChangeSupport.firePropertyChange("org_id", oldOrg_id, org_id);
    }

    public Integer getOrg_id() {
        return org_id;
    }
    protected String subinv_desc;
    protected Integer org_id; 
    
    public SubinvBean() {
        super();
    }

    public boolean equals(Object object) {
    if (this == object) {
    return true;
    }
    if (!(object instanceof SubinvBean)) {
    return false;
    }
    final SubinvBean other = (SubinvBean)object;
    if (!(subinv_code == null ? other.subinv_code == null : subinv_code.equals(other.subinv_code))) {
    return false;
    }
    return true;
    }

    public int hashCode() {
    final int PRIME = 37;
    int result = 1;
    result = PRIME * result + ((subinv_code == null) ? 0 : subinv_code.hashCode());
    return result;
    }

    public int compareTo(Object o) {
    if (this.equals(o)){
    return 0;
    }
    else if (o instanceof SubinvBean){
    return this.subinv_desc.compareTo(((SubinvBean)o).getSubinv_desc());
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
