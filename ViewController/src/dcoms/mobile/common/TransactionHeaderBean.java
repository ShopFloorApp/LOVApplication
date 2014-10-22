package dcoms.mobile.common;

import java.util.Date;

import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;

public class TransactionHeaderBean {
    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    public TransactionHeaderBean() {
        super();
    }
    protected String CallingSystemName;
    protected String CallingInterfaceName;
    protected String UserIdentifier;
    protected String RoleName;
    protected Date LastSyncDateTime;
    protected StatusBean Status;

    public void setCallingSystemName(String CallingSystemName) {
        String oldCallingSystemName = this.CallingSystemName;
        this.CallingSystemName = CallingSystemName;
        propertyChangeSupport.firePropertyChange("CallingSystemName", oldCallingSystemName, CallingSystemName);
    }

    public String getCallingSystemName() {
        return CallingSystemName;
    }

    public void setCallingInterfaceName(String CallingInterfaceName) {
        String oldCallingInterfaceName = this.CallingInterfaceName;
        this.CallingInterfaceName = CallingInterfaceName;
        propertyChangeSupport.firePropertyChange("CallingInterfaceName", oldCallingInterfaceName, CallingInterfaceName);
    }

    public String getCallingInterfaceName() {
        return CallingInterfaceName;
    }

    public void setUserIdentifier(String UserIdentifier) {
        String oldUserIdentifier = this.UserIdentifier;
        this.UserIdentifier = UserIdentifier;
        propertyChangeSupport.firePropertyChange("UserIdentifier", oldUserIdentifier, UserIdentifier);
    }

    public String getUserIdentifier() {
        return UserIdentifier;
    }

    public void setRoleName(String RoleName) {
        String oldRoleName = this.RoleName;
        this.RoleName = RoleName;
        propertyChangeSupport.firePropertyChange("RoleName", oldRoleName, RoleName);
    }

    public String getRoleName() {
        return RoleName;
    }

    public void setLastSyncDateTime(Date LastSyncDateTime) {
        Date oldLastSyncDateTime = this.LastSyncDateTime;
        this.LastSyncDateTime = LastSyncDateTime;
        propertyChangeSupport.firePropertyChange("LastSyncDateTime", oldLastSyncDateTime, LastSyncDateTime);
    }

    public Date getLastSyncDateTime() {
        return LastSyncDateTime;
    }

    public void setStatus(StatusBean Status) {
        StatusBean oldStatus = this.Status;
        this.Status = Status;
        propertyChangeSupport.firePropertyChange("Status", oldStatus, Status);
    }

    public StatusBean getStatus() {
        return Status;
    }
 
    public void addPropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.addPropertyChangeListener(l);
    }

    public void removePropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.removePropertyChangeListener(l);
    }
}
