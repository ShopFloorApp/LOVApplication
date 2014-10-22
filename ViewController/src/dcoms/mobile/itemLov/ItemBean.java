package dcoms.mobile.itemLov;

import dcoms.mobile.common.AttributeListBean;



import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;

public class ItemBean implements Comparable{
    
    private String item_num;
    private String item_desc;
    private Integer org_id;
    
    private transient PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    public ItemBean() {
        super();
    }

    public boolean equals(Object object) {
    if (this == object) {
    return true;
    }
    if (!(object instanceof ItemBean)) {
    return false;
    }
    final ItemBean other = (ItemBean)object;
    if (!(item_num == null ? other.item_num == null : item_num.equals(other.item_num))) {
    return false;
    }
    return true;
    }

    public int hashCode() {
    final int PRIME = 37;
    int result = 1;
    result = PRIME * result + ((item_num == null) ? 0 : item_num.hashCode());
    return result;
    }

    public int compareTo(Object o) {
    if (this.equals(o)){
    return 0;
    }
    else if (o instanceof ItemBean){
    return this.item_desc.compareTo(((ItemBean)o).getDesc());
    }
    else{
    throw new ClassCastException("CountryBO expected.");
    }
    }

    public void setItemNum(String ItemNum) {
        String oldItemNum = this.item_num;
        this.item_num = ItemNum;
        propertyChangeSupport.firePropertyChange("InventoryItemNum", oldItemNum, ItemNum);
    }

    public String getItemNum() {
        return item_num;
    }

    public void setDesc(String Desc) {
        String oldDesc = this.item_desc;
        this.item_desc = Desc;
        propertyChangeSupport.firePropertyChange("Desc", oldDesc, Desc);
    }

    public String getDesc() {
        return item_desc;
    }

    public void setOrgID(Integer OrgID) {
        Integer oldOrgID = this.org_id;
        this.org_id = OrgID;
        propertyChangeSupport.firePropertyChange("OrgID", oldOrgID, OrgID);
    }

    public Integer getOrgID() {
        return org_id;
    }


    public void addPropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.addPropertyChangeListener(l);
    }

    public void removePropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.removePropertyChangeListener(l);
    }
}
