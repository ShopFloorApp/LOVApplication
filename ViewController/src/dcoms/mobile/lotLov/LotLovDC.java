package dcoms.mobile.lotLov;

import dcoms.mobile.common.StatusBean;
import dcoms.mobile.common.TransactionHeaderBean;


import dcoms.mobile.serialLov.SerialBean;
import dcoms.mobile.serialLov.SerialRequestBean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.el.ValueExpression;

import oracle.adfmf.framework.api.AdfmfJavaUtilities;
import oracle.adfmf.framework.api.GenericTypeBeanSerializationHelper;
import oracle.adfmf.framework.exception.AdfException;
import oracle.adfmf.framework.exception.AdfInvocationException;
import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;
import oracle.adfmf.java.beans.ProviderChangeListener;
import oracle.adfmf.java.beans.ProviderChangeSupport;
import oracle.adfmf.util.GenericType;
import oracle.adfmf.util.GenericVirtualType;

public class LotLovDC {
    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
    private ProviderChangeSupport providerChangeSupport = new ProviderChangeSupport(this);

    public LotLovDC() {
        super();
    }
    protected String lotNum;
    List s_lotList = new ArrayList();

    public LotBean[] getAllLot() {
        System.out.println("Inside getAllLots method " + s_lotList.size());
        LotBean[] lotArray = (LotBean[]) s_lotList.toArray(new LotBean[s_lotList.size()]);
        return lotArray;
    }

    public void setLotNum(String lotNum) {
        String oldLotNum = this.lotNum;
        this.lotNum = lotNum;
        propertyChangeSupport.firePropertyChange("lotNum", oldLotNum, lotNum);
    }

    public String getLotNum() {
        return lotNum;
    }

    public void invokeWS() throws AdfInvocationException {
        s_lotList.clear();
        List pnames = new ArrayList(1);
        List pvals = new ArrayList(1);
        List ptypes = new ArrayList(1);
        LotRequestBean payload = new LotRequestBean();
        TransactionHeaderBean payloadTH = new TransactionHeaderBean();
        StatusBean status = new StatusBean();
        GenericVirtualType payloadGVT = new GenericVirtualType(null, "payload");
        GenericVirtualType headerGVT = new GenericVirtualType(null, "TransactionHeader");
        GenericVirtualType statusGVT = new GenericVirtualType(null, "Status");
        GenericType resultGT;

        String orgId = "100";
        // (String) AdfmfJavaUtilities.evaluateELExpression("#{preferenceScope.feature.dcoms.myWarehouseFeature.OrgIdPG.OrgId}");
        String orgCode = "102";
        String Action="0";
        String Item="0";
        //(String) AdfmfJavaUtilities.evaluateELExpression("#{preferenceScope.feature.dcoms.myWarehouseFeature.OrgIdPG.OrgCode}");
        System.out.println("orgId " + orgId);
        System.out.println("orgCode " + orgCode);

        payloadGVT.defineAttribute(null, "Whse", String.class, orgCode);
        payloadGVT.defineAttribute(null, "LotNum", String.class, getLotNum());
        headerGVT.defineAttribute(null, "CallingSystemName", String.class, "1");
        headerGVT.defineAttribute(null, "CallingInterfaceName", String.class, "1");
        
        ValueExpression ve = null;
        //ve = AdfmfJavaUtilities.getValueExpression("#{securityContext.userName}", String.class);
        String userId = "dcom"; // (String) ve.getValue(AdfmfJavaUtilities.getAdfELContext());
        System.out.println("userId " + userId);
        headerGVT.defineAttribute(null, "UserIdentifier", String.class, userId);
        headerGVT.defineAttribute(null, "RoleName", String.class, "1");
        headerGVT.defineAttribute(null, "LastSyncDateTime", Date.class, new Date());

        statusGVT.defineAttribute(null, "Code", String.class, "1");
        statusGVT.defineAttribute(null, "Msg", String.class, "1");
        headerGVT.defineAttribute(null, "Status", GenericType.class, "Status");
        payloadGVT.defineAttribute(null, "TransactionHeader", GenericType.class, "TransactionHeader");


        payloadTH.setCallingSystemName("3");
        payloadTH.setCallingInterfaceName("2");

        payloadTH.setUserIdentifier(userId);
        payloadTH.setRoleName("UMX");
        payloadTH.setStatus(status);
        
        payloadTH.setLastSyncDateTime(new Date());
        System.out.println("Called ProcessWS1");

        payload.setTransactionHeader(payloadTH);
        payload.setWhse(orgCode);
        if (getLotNum() == null) {
            throw new AdfException("Please enter search criteria for Lot Number", AdfException.ERROR);
        }
        String sNo = getLotNum();
        if (sNo != null && sNo.length() > 0 && sNo.length() < 3) {
            throw new AdfException("Please enter atleast 3 characters for Lot Number", AdfException.ERROR);
        }
        payload.setLotNum(getLotNum());
        payload.setItem(Item);
        payload.setAction(Action);
        payload.setSubinv(Action);
        payload.setLocator(Action);
        System.out.println("Before " + payload.getLotNum());
        System.out.println("Before " + payload.getWhse());
        System.out.println("Before " + payload.getItem());
        System.out.println("Before " + payload.getSubinv());
        System.out.println("Before " + payload.getLocator());
        System.out.println("Before " + payload.getAction());
               
        
        System.out.println("Before convert");
        
        System.out.println("Converted to generic type " + payloadGVT.getNamespace());
        pnames.add("payload");
        ptypes.add(GenericType.class);
        pvals.add(payloadGVT);
        

        try {
            System.out.println("Before Service Invoke");
            resultGT =
                (GenericType) AdfmfJavaUtilities.invokeDataControlMethod("LotWS", null, "process", pnames, pvals,
                                                                         ptypes);
            System.out.println("After Service Invoke");
            GenericType result_collection = (GenericType) resultGT.getAttribute(0);
            System.out.println("result  reusltcollection" + result_collection.getAttribute(1));
            GenericType lotCollection = (GenericType) result_collection.getAttribute(1);
            System.out.println("result lotcollection " + lotCollection.getNamespace());
            System.out.println("result count " + lotCollection.getAttributeCount());
            if (lotCollection.getAttributeCount() == 0) {
                s_lotList.clear();
                ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.lotLovResults}", String.class);
                ve.setValue(AdfmfJavaUtilities.getAdfELContext(), "No search results");
            } else {
                ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.lotLovResults}", String.class);
                ve.setValue(AdfmfJavaUtilities.getAdfELContext(), "Search results");
            }
            for (int i = 0; i < lotCollection.getAttributeCount(); i++) {
                GenericType gtlotObj = (GenericType) lotCollection.getAttribute(i);
                System.out.println("gtlotObj is  " + gtlotObj);
                LotBean lotObj = new LotBean();
                lotObj = (LotBean) GenericTypeBeanSerializationHelper.fromGenericType(LotBean.class, gtlotObj);

                s_lotList.add(lotObj);
            }
            providerChangeSupport.fireProviderRefresh("allLots");
        } catch (AdfInvocationException ex) {
            AdfException e = new AdfException("Error Invoking WebService.Please try later", AdfException.WARNING);
            System.out.println("Error Invoking WebService.Please try later");
        }
    }


    public void addPropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.addPropertyChangeListener(l);
    }

    public void removePropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.removePropertyChangeListener(l);
    }

    public void addProviderChangeListener(ProviderChangeListener l) {
        providerChangeSupport.addProviderChangeListener(l);
    }

    public void removeProviderChangeListener(ProviderChangeListener l) {
        providerChangeSupport.removeProviderChangeListener(l);
    }
}
