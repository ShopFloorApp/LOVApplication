package dcoms.mobile.serialLov;

import dcoms.mobile.common.StatusBean;
import dcoms.mobile.common.TransactionHeaderBean;

import java.text.DateFormat;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Collections;
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

public class SerialLovDC {
    List s_serialList = new ArrayList();
    protected String serialNum;
    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
    private ProviderChangeSupport providerChangeSupport = new ProviderChangeSupport(this);

    public void setSerialNum(String serialNum) {
        String oldSerialNum = this.serialNum;
        this.serialNum = serialNum;
        propertyChangeSupport.firePropertyChange("serialNum", oldSerialNum, serialNum);
    }

    public String getSerialNum() {
        return serialNum;
    }

    public SerialBean[] getAllSerials() {
        System.out.println("Inside getAllSerials method " + s_serialList.size());
        SerialBean[] allSerials = (SerialBean[]) s_serialList.toArray(new SerialBean[s_serialList.size()]);
        return allSerials;
    }

    public SerialLovDC() {
        super();
    }

    public void invokeWS() throws AdfInvocationException {
        System.out.println("InvokeWS");
        s_serialList.clear();
        List pnames = new ArrayList(1);
        List pvals = new ArrayList(1);
        List ptypes = new ArrayList(1);
        SerialRequestBean payload = new SerialRequestBean();
        TransactionHeaderBean payloadTH = new TransactionHeaderBean();
        StatusBean status = new StatusBean();
        GenericVirtualType payloadGVT = new GenericVirtualType(null, "payload");
        GenericVirtualType headerGVT = new GenericVirtualType(null, "TransactionHeader");
        GenericVirtualType statusGVT = new GenericVirtualType(null, "Status");
        GenericType result;
        
        String orgId =
            (String) AdfmfJavaUtilities.evaluateELExpression("#{preferenceScope.feature.dcoms.myWarehouseFeature.OrgIdPG.OrgId}");
        String orgCode =
            (String) AdfmfJavaUtilities.evaluateELExpression("#{preferenceScope.feature.dcoms.myWarehouseFeature.OrgIdPG.OrgCode}");
        System.out.println("orgId " + orgId);
        System.out.println("orgCode " + orgCode);
        
                    payloadGVT.defineAttribute(null, "Whse", String.class, orgCode);
                    payloadGVT.defineAttribute(null, "SerialNum", String.class, getSerialNum());
                    headerGVT.defineAttribute(null, "CallingSystemName", String.class, "1");
                    headerGVT.defineAttribute(null, "CallingInterfaceName", String.class, "1");
        ValueExpression ve = null;
        ve = AdfmfJavaUtilities.getValueExpression("#{securityContext.userName}", String.class);
        String userId = (String) ve.getValue(AdfmfJavaUtilities.getAdfELContext());
                    headerGVT.defineAttribute(null, "UserIdentifier", String.class, userId);
                    headerGVT.defineAttribute(null, "RoleName", String.class, "1");
                    headerGVT.defineAttribute(null, "LastSyncDateTime", Date.class, new Date());
                    
                    statusGVT.defineAttribute(null, "Code", String.class, "1");
                    statusGVT.defineAttribute(null, "Msg", String.class, "1");
                    headerGVT.defineAttribute(null, "Status", GenericType.class, "Status");
                    payloadGVT.defineAttribute(null, "TransactionHeader", GenericType.class, "TransactionHeader");
                    
        status.setCode("Code");
        status.setMsg("Msg");

        payloadTH.setCallingSystemName("3");
        payloadTH.setCallingInterfaceName("2");
       
        payloadTH.setUserIdentifier(userId);
        payloadTH.setRoleName("UMX");
        payloadTH.setLastSyncDateTime(new Date());
        payloadTH.setStatus(status);
        System.out.println("Called ProcessWS1");

        payload.setTransactionHeader(payloadTH);
        payload.setWhse(orgCode);
        if (getSerialNum() == null) {
            throw new AdfException("Please enter search criteria for Serial Number", AdfException.ERROR);
        }
        String sNo = getSerialNum();
        if (sNo != null && sNo.length() > 0 && sNo.length() < 3) {
            throw new AdfException("Please enter atleast 3 characters for serial Number", AdfException.ERROR);
        }
        payload.setSerialNum(getSerialNum());
        System.out.println("Before convert");
        
       // GenericType payloadGT =
         //   GenericTypeBeanSerializationHelper.toGenericType("DCOMGetSerialWS.Types.process.payload", payload);
        System.out.println("Converted to generic type " + payloadGVT.getNamespace());
        pnames.add("payload");
        ptypes.add(GenericType.class);
        pvals.add(payloadGVT);

        try {
            System.out.println("Before Service Invoke");
            result =
                (GenericType) AdfmfJavaUtilities.invokeDataControlMethod("DCOMGetSerialWS", null, "process", pnames,
                                                                         pvals, ptypes);
           /* System.out.println("After Service Invoke");
            GenericType result_collection = (GenericType) resultGT.getAttribute(0);
            //System.out.println("result  reusltcollection" + result_collection.getAttribute(1));
            //System.out.println("result  reusltcollection done" );                   
            GenericType serialCollection = ((GenericType) result_collection.getAttribute(1));
            //System.out.println("result serialcollection " + serialCollection.getNamespace().toString());
          //  System.out.println("result count " + serialCollection.getAttributeCount());
            if (serialCollection.getAttributeCount() == 0) {
                s_serialList.clear();
                ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.serialLovResults}", String.class);
                ve.setValue(AdfmfJavaUtilities.getAdfELContext(), "No search results");
            } else {
                ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.serialLovResults}", String.class);
                ve.setValue(AdfmfJavaUtilities.getAdfELContext(), "Search results");
            }
            for (int i = 0; i < serialCollection.getAttributeCount(); i++) {
                GenericType gtserialObj = (GenericType) serialCollection.getAttribute(i);
                System.out.println("gtserialObj is  " + gtserialObj);
                SerialBean serialObj = new SerialBean();
                serialObj =
                    (SerialBean) GenericTypeBeanSerializationHelper.fromGenericType(SerialBean.class, gtserialObj);
                System.out.println("desc " + serialObj.getSerialStatus());
                System.out.println("serial id " + serialObj.getSerialNum());
                s_serialList.add(serialObj);
            }
            providerChangeSupport.fireProviderRefresh("allSerials");
        } catch (AdfInvocationException ex) {
            AdfException e = new AdfException("Error Invoking WebService.Please try later", AdfException.WARNING);
            System.out.println("Error Invoking WebService.Please try later");
        }*/
            
            //ashish
            System.out.println("After Service Invoke");
            System.out.println("After Service Invoke - AttributeCount" + result.getAttributeCount());
            String ns = result.getNamespace(); //Get the namespace of the data provider object
            System.out.println("Print Result Namespace -" + ns);
            String name = result.getName(); //Get the Name of the dataprovider object
            System.out.println("Print Result Name -" + name);
            String type = result.getType(); //Get the type of the data provider object
            System.out.println("Print Result Type -" + type);
            if (result.isComplexType()) {

                System.out.println("Print Result is complex");

            } else {
                System.out.println("Print Result is not complex");
            }

            GenericType Result_Collection =
                (GenericType)result.getAttribute(0); //Get Hold of the Result Collection

            System.out.println("After Conversion to Result_Collection");
            System.out.println("After Service Invoke - AttributeCount" + Result_Collection.getAttributeCount());
            String ns0 = Result_Collection.getNamespace(); //Get the namespace of the data provider object
            System.out.println("Print Result Namespace -" + ns0);
            String name0 = Result_Collection.getName(); //Get the Name of the dataprovider object
            System.out.println("Print Result Name -" + name0);
            String type0 = Result_Collection.getType(); //Get the type of the data provider object
            System.out.println("Print Result Type -" + type0);
            if (Result_Collection.isComplexType()) {

                System.out.println("Print Result Result_Collection is complex");

            } else {
                System.out.println("Print Result Result_Collection is not complex");
            }

            //Department List
            GenericType serialLst =
                (GenericType)Result_Collection.getAttribute(1); //Get Hold of the Result Collection

            System.out.println("After Conversion to Result_Collection");
            System.out.println("After Service Invoke - AttributeCount" + serialLst.getAttributeCount());
            String ns2 = serialLst.getNamespace(); //Get the namespace of the data provider object
            System.out.println("Print deptLst Namespace -" + ns2);
            String name2 = serialLst.getName(); //Get the Name of the dataprovider object
            System.out.println("Print deptLst Name -" + name2);
            String type2 = serialLst.getType(); //Get the type of the data provider object
            System.out.println("Print deptLst Type -" + type2);
            if (serialLst.isComplexType()) {

                System.out.println("Print deptLst is complex");

            } else {
                System.out.println("Print deptLst is not complex");
            }

            //Departments
            for (int i = 0; i < serialLst.getAttributeCount(); i++) {
                GenericType dept = (GenericType)serialLst.getAttribute(i); //Get Hold of the Result Collection

                System.out.println("After Conversion to dept");
                System.out.println("After Service Invoke - AttributeCount" + dept.getAttributeCount());
                String ns3 = dept.getNamespace(); //Get the namespace of the data provider object
                System.out.println("Print dept Namespace -" + ns3);
                String name3 = dept.getName(); //Get the Name of the dataprovider object
                System.out.println("Print dept Name -" + name3);
                String type3 = dept.getType(); //Get the type of the data provider object
                System.out.println("Print dept Type -" + type3);

                if (dept.isComplexType()) {

                    System.out.println("Print dept is complex");

                } else {
                    System.out.println("Print dept is not complex");
                }

                SerialBean dept1 =
                    (SerialBean)GenericTypeBeanSerializationHelper.fromGenericType(SerialBean.class, dept);

                System.out.println("After Serial");
                s_serialList.add(dept1);
                System.out.println("After Job");
                providerChangeSupport.fireProviderRefresh("allSerials");
            }

            } catch (AdfInvocationException e) {
            AdfException e1 = new AdfException("Error Invoking WebService.Please try later", AdfException.WARNING);
            System.out.println("Inside Exception block");
            } catch (Exception ex) {
            AdfException e1 =
                new AdfException("Exception occured while reading Departments", AdfException.WARNING);
            System.out.println("Inside Exception block");
            }

        
            // Collections.sort(s_departments);
            Collections.sort(s_serialList);
            //ashish
            
            
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
