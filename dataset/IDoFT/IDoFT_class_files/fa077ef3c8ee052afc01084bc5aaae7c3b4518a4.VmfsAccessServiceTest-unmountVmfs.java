package com.huawei.dmestore.services;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyList;
import static org.mockito.Matchers.anyMap;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.huawei.dmestore.constant.DmeConstants;
import com.huawei.dmestore.dao.DmeVmwareRalationDao;
import com.huawei.dmestore.entity.DmeVmwareRelation;
import com.huawei.dmestore.entity.VCenterInfo;
import com.huawei.dmestore.model.Storage;
import com.huawei.dmestore.utils.ToolUtils;
import com.huawei.dmestore.utils.VCSDKUtils;
import com.huawei.vmware.VcConnectionHelpers;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.vmware.vim25.ManagedObjectReference;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName VmfsAccessServiceTest.java
 * @Description TODO
 * @createTime 2020年11月19日 17:21:00
 */
public class VmfsAccessServiceTest {
    Gson gson = new Gson();

    @Mock
    private DmeVmwareRalationDao dmeVmwareRalationDao;

    @Mock
    private DmeAccessService dmeAccessService;

    @Mock
    private DmeStorageService dmeStorageService;

    @Mock
    private DataStoreStatisticHistoryService dataStoreStatisticHistoryService;

    @Mock
    private VCSDKUtils vcsdkUtils;

    @Mock
    private TaskService taskService;

    @Mock
    private VCenterInfoService vCenterInfoService;

    @InjectMocks
    private VmfsAccessService vmfsAccessService = new VmfsAccessServiceImpl();

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void listVmfs() throws Exception {
        List<DmeVmwareRelation> dvrlist = new ArrayList<>();
        DmeVmwareRelation dmeVmwareRelation = new DmeVmwareRelation();
        String volumeId = "8f6d93f1-4214-46bc-ae7a-85f8349ebbd2";
        String storeObjectId = "13232";
        dmeVmwareRelation.setStoreId(storeObjectId);
        dmeVmwareRelation.setVolumeId(volumeId);
        dvrlist.add(dmeVmwareRelation);
        when(dmeVmwareRalationDao.getDmeVmwareRelation(DmeConstants.STORE_TYPE_VMFS)).thenReturn(dvrlist);
        List<Storage> list = new ArrayList<>();
        Storage storageObj = new Storage();
        storageObj.setName("Huawei.Storage");
        list.add(storageObj);
        when(dmeStorageService.getStorages()).thenReturn(list);
        List<Map<String, Object>> lists = new ArrayList<>();
        Map<String, Object> dsmap = new HashMap<>();
        dsmap.put("objectid", storeObjectId);
        dsmap.put("capacity", 4.75);
        dsmap.put("freeSpace", 4.052734375);
        dsmap.put("uncommitted", 4.15);
        List<String> wwnList = new ArrayList(16);
        wwnList.add(volumeId);
        dsmap.put("vmfsWwnList", wwnList);
        lists.add(dsmap);
        String listStr = gson.toJson(lists);
        when(vcsdkUtils.getAllVmfsDataStoreInfos(DmeConstants.STORE_TYPE_VMFS)).thenReturn(listStr);
        String url = "/rest/blockservice/v1/volumes" + "/" + volumeId;
        String jsonData
            = "{\"volume\": {\"id\": \"955a0632-c309-4471-a116-6a059d84ade3\",\"name\": \"VMFSTest20201026\",\"description\": null,\"status\": \"normal\",\"attached\": true,\"project_id\": null,\"alloctype\": \"thick\",\"capacity\": 1,\"service_level_name\": \"cctest\",\"attachments\": [{\"id\": \"8b561dd2-03bb-4f20-98c4-8092e75fe951\",\"volume_id\": \"955a0632-c309-4471-a116-6a059d84ade3\",\"host_id\": \"9cbd24b5-fb5b-4ad9-9393-cf05b9b97339\",\"attached_at\": \"2020-10-26T06:50:20.000000\",\"attached_host_group\": null}],\"volume_raw_id\": \"174\",\"volume_wwn\": \"67c1cf11005893452a7c7314000000ae\",\"storage_id\": \"b94bff9d-0dfb-11eb-bd3d-0050568491c9\",\"storage_sn\": \"2102351QLH9WK5800028\",\"pool_raw_id\": \"0\",\"capacity_usage\": null,\"protected\": false,\"updated_at\": \"2020-10-26T06:50:20.000000\",\"created_at\": \"2020-10-26T06:50:15.000000\",\"tuning\": {\"smarttier\": \"0\",\"dedupe_enabled\": null,\"compression_enabled\": null,\"workload_type_id\": null,\"smartqos\": {\"maxiops\": 123,\"miniops\": 2134,\"maxbandwidth\": 123413,\"minbandwidth\": 1234,\"latency\": 0.48},\"alloctype\": \"thick\"},\"initial_distribute_policy\": \"0\",\"prefetch_policy\": \"3\",\"owner_controller\": \"0B\",\"prefetch_value\": \"0\"}}";
        ResponseEntity<String> responseEntity = new ResponseEntity<>(jsonData, null, HttpStatus.OK);
        when(dmeAccessService.access(url, HttpMethod.GET, null)).thenReturn(responseEntity);
        vmfsAccessService.listVmfs();
    }

    @Test
    public void listVmfsPerformance() throws Exception {
        String jsonStr
            = "{\"67c1cf1100589345402376ae00000143\": {\"1125921381744657\": {\"min\": {\"1605755940000\": 0.0},\"avg\": {\"0\": 0.0},\"max\": {\"1605755940000\": 0.0},\"series\": [{\"1605755940000\": 0.0}]}}}";
        Map<String, Object> remap = gson.fromJson(jsonStr, Map.class);
        List<String> wwns = new ArrayList<>();
        wwns.add("67c1cf1100589345402376ae00000143");
        Map<String, Object> params = new HashMap<>(16);
        params.put("obj_ids", wwns);
        when(dataStoreStatisticHistoryService.queryVmfsStatisticCurrent(params)).thenReturn(remap);

        vmfsAccessService.listVmfsPerformance(wwns);
    }

    @Test
    public void createVmfs() throws Exception {
        String requestParams
            = "{\"name\":\"testvmfsWxy\",\"volumeName\":\"testvmfsWxy\",\"isSameName\":true,\"capacity\":1,\"capacityUnit\":\"GB\",\"count\":1,\"service_level_id\":\"0927dbb9-9e7a-43ee-9427-02c14963290e\",\"service_level_name\":\"cctest\",\"version\":\"5\",\"blockSize\":1024,\"spaceReclamationGranularity\":1024,\"spaceReclamationPriority\":\"low\",\"host\":\"10.143.133.17\",\"hostId\":\"urn:vmomi:HostSystem:host-1034:674908e5-ab21-4079-9cb1-596358ee5dd1\",\"cluster\":null,\"clusterId\":null,\"storage_id\":null,\"pool_raw_id\":null,\"workload_type_id\":null,\"alloctype\":null,\"control_policy\":null,\"latencyChoose\":false,\"latency\":null,\"maxbandwidth\":null,\"maxbandwidthChoose\":false,\"maxiops\":null,\"maxiopsChoose\":false,\"minbandwidth\":null,\"minbandwidthChoose\":false,\"miniops\":null,\"miniopsChoose\":false,\"qosname\":null,\"deviceName\":null,\"hostDataloadSuccess\":true,\"culDataloadSuccess\":true}";
        Map<String, Object> params = gson.fromJson(requestParams, Map.class);
        String hostId = "urn:vmomi:HostSystem:host-1034:674908e5-ab21-4079-9cb1-596358ee5dd1";
        String hostIp = "10.143.133.17";
        String demHostId = "9cbd24b5-fb5b-4ad9-9393-cf05b9b97339";
        int capacity = 1;

        String hbasStr = "[{\"name\":\"iqn.1994-05.com.redhat:b7b236e46d8e\",\"type\":\"ISCSI\"}]";
        List<Map<String, Object>> hbas = gson.fromJson(hbasStr,
            new TypeToken<List<Map<String, Object>>>() { }.getType());
        when(vcsdkUtils.getHbasByHostObjectId(hostId)).thenReturn(hbas);

        String hostListStr
            = "[{\"managed_status\":\"NORMAL\",\"project_id\":\"\",\"ip\":\"10.143.133.118\",\"os_type\":\"LINUX\",\"name\":\"im_1node\",\"overall_status\":\"\",\"initiator_count\":1,\"id\":\"332eb0e9-5e87-4730-8cdd-67b08972010b\",\"os_status\":\"ONLINE\",\"access_mode\":\"NONE\",\"display_status\":\"NORMAL\"},{\"managed_status\":\"NORMAL\",\"project_id\":\"\",\"ip\":\"10.143.133.18\",\"os_type\":\"LINUX\",\"name\":\"10.143.133.18\",\"overall_status\":\"\",\"initiator_count\":1,\"id\":\"8cedbf3c-a65a-43f7-9366-d9c4d9601623\",\"os_status\":\"ONLINE\",\"access_mode\":\"NONE\",\"display_status\":\"NORMAL\"},{\"managed_status\":\"NORMAL\",\"project_id\":\"\",\"ip\":\"10.143.133.15\",\"os_type\":\"LINUX\",\"name\":\"10.143.133.15\",\"overall_status\":\"\",\"initiator_count\":1,\"id\":\"c3463836-b58a-4a33-815c-d32c52e606d2\",\"os_status\":\"ONLINE\",\"access_mode\":\"NONE\",\"display_status\":\"NORMAL\"},{\"managed_status\":\"NORMAL\",\"project_id\":\"\",\"ip\":\"10.143.133.17\",\"os_type\":\"LINUX\",\"name\":\"10.143.133.17\",\"overall_status\":\"\",\"initiator_count\":1,\"id\":\"9cbd24b5-fb5b-4ad9-9393-cf05b9b97339\",\"os_status\":\"ONLINE\",\"access_mode\":\"NONE\",\"display_status\":\"NORMAL\"},{\"managed_status\":\"NORMAL\",\"hostGroups\":[{\"name\":\"Host003\",\"id\":\"34108dcd-ac32-48ab-8ef8-62c59acefce4\"}],\"project_id\":\"\",\"ip\":\"\",\"os_type\":\"LINUX\",\"name\":\"Host003\",\"overall_status\":\"\",\"initiator_count\":1,\"id\":\"34108dcd-ac32-48ab-8ef8-62c59acefce4\",\"os_status\":\"ONLINE\",\"access_mode\":\"NONE\",\"display_status\":\"NORMAL\"},{\"managed_status\":\"NORMAL\",\"hostGroups\":[{\"name\":\"10.143.133.196\",\"id\":\"4f987715-19d5-4d63-85e4-b672e814705b\"},{\"name\":\"10.143.133.196\",\"id\":\"4f987715-19d5-4d63-85e4-b672e814705b\"}],\"project_id\":\"\",\"ip\":\"10.143.133.196\",\"os_type\":\"LINUX\",\"name\":\"10.143.133.196\",\"overall_status\":\"\",\"initiator_count\":1,\"id\":\"4f987715-19d5-4d63-85e4-b672e814705b\",\"os_status\":\"ONLINE\",\"access_mode\":\"NONE\",\"display_status\":\"NORMAL\"}]";
        List<Map<String, Object>> hostList = gson.fromJson(hostListStr,
            new TypeToken<List<Map<String, Object>>>() { }.getType());
        when(dmeAccessService.getDmeHosts(null)).thenReturn(hostList);

        String initiatorStr
            = "[{\"port_name\":\"iqn.1994-05.com.redhat:b7b236e46d8e\",\"protocol\":\"ISCSI\",\"id\":\"0d922e34-0dc0-45d8-a4c0-5f9135cd9661\",\"status\":\"UNKNOWN\"}]";
        List<Map<String, Object>> initiators = gson.fromJson(initiatorStr,
            new TypeToken<List<Map<String, Object>>>() { }.getType());
        when(dmeAccessService.getDmeHostInitiators(demHostId)).thenReturn(initiators);

        String hostMapStr = "";
        Map<String, Object> hostMap = gson.fromJson(hostMapStr, new TypeToken<Map<String, Object>>() { }.getType());
        Map<String, Object> param = new HashMap<>();
        param.put("host", hostIp);
        param.put("hostId", hostId);
        when(dmeAccessService.createHost(param)).thenReturn(hostMap);

        String url = "/rest/blockservice/v1/volumes";
        String createBody
            = "{\"mapping\":{\"host_id\":\"9cbd24b5-fb5b-4ad9-9393-cf05b9b97339\"},\"volumes\":[{\"name\":\"testvmfsWxy\",\"count\":0,\"capacity\":0}],\"service_level_id\":\"0927dbb9-9e7a-43ee-9427-02c14963290e\"}";
        String s = "{\"task_id\":\"1231123213\"}";
        ResponseEntity responseEntity = new ResponseEntity<>(s, null, HttpStatus.ACCEPTED);
        when(dmeAccessService.access(url, HttpMethod.POST, createBody)).thenReturn(responseEntity);

        String createUnLevelBody
            = "{\"customize_volumes\":{\"storage_id\":\"132341\",\"volume_specs\":[{\"name\":\"testvmfsWxy\",\"count\":0,\"capacity\":0}]},\"mapping\":{\"host_id\":\"9cbd24b5-fb5b-4ad9-9393-cf05b9b97339\"}}";
        String urlUnLevel = "/rest/blockservice/v1/volumes/customize-volumes";
        when(dmeAccessService.access(urlUnLevel, HttpMethod.POST, createUnLevelBody)).thenReturn(responseEntity);

        when(taskService.checkTaskStatus(anyList())).thenReturn(true);

        String listVolumeUnUrl
            = "/rest/blockservice/v1/volumes?name=testvmfsWxy&host_id=9cbd24b5-fb5b-4ad9-9393-cf05b9b97339&storage_id=132341";
        String listVolumeUrl
            = "/rest/blockservice/v1/volumes?name=testvmfsWxy01&host_id=9cbd24b5-fb5b-4ad9-9393-cf05b9b97339&service_level_id=0927dbb9-9e7a-43ee-9427-02c14963290e";
        String listVolumeStr
            = "{\"volumes\":[{\"id\":\"621c7b6e-82d1-4db4-9567-c4c0a3822225\",\"name\":\"testvmfsWxy0011\",\"description\":null,\"status\":\"normal\",\"attached\":true,\"project_id\":null,\"alloctype\":\"thick\",\"capacity\":1,\"service_level_name\":\"cctest\",\"attachments\":[{\"id\":\"ab5c7826-17f7-464a-9069-df6c12938aa7\",\"volume_id\":\"621c7b6e-82d1-4db4-9567-c4c0a3822225\",\"host_id\":\"9cbd24b5-fb5b-4ad9-9393-cf05b9b97339\",\"attached_at\":\"2020-11-20T08:31:40.000000\",\"attached_host_group\":null}],\"volume_raw_id\":\"325\",\"volume_wwn\":\"67c1cf11005893454ac38ca800000145\",\"storage_id\":\"b94bff9d-0dfb-11eb-bd3d-0050568491c9\",\"storage_sn\":\"2102351QLH9WK5800028\",\"pool_raw_id\":\"0\",\"capacity_usage\":null,\"protected\":false,\"updated_at\":\"2020-11-20T08:31:40.000000\",\"created_at\":\"2020-11-20T08:31:34.000000\"}],\"count\":1}";
        ResponseEntity responseEntity1 = new ResponseEntity<>(listVolumeStr, null, HttpStatus.OK);
        when(dmeAccessService.access(listVolumeUrl, HttpMethod.GET, null)).thenReturn(responseEntity1);
        when(dmeAccessService.access(listVolumeUnUrl, HttpMethod.GET, null)).thenReturn(responseEntity1);

        VCenterInfo vCenterInfo = mock(VCenterInfo.class);
        when(vCenterInfoService.getVcenterInfo()).thenReturn(vCenterInfo);

        String volumeWwn = "67c1cf11005893454ac38ca800000145";
        when(vcsdkUtils.getLunsOnHost(hostId, capacity, volumeWwn)).thenReturn(mock(Map.class));
        when(vcsdkUtils.getLunsOnCluster(hostId, capacity, volumeWwn)).thenReturn(mock(Map.class));
        String dataStoreStr
            = "{\"hostName\":\"10.143.133.17\",\"name\":\"testvmfsWxy0011\",\"id\":\"datastore-1247\",\"type\":\"Datastore\",\"objectId\":\"urn:vmomi:Datastore:datastore-1247:674908e5-ab21-4079-9cb1-596358ee5dd1\",\"capacity\":805306368}";
        when(vcsdkUtils.createVmfsDataStore(anyMap(), anyInt(), anyString(), anyInt(), anyInt(), anyInt(), anyString()))
            .thenReturn(dataStoreStr);

        doNothing().when(vcsdkUtils).scanDataStore(null, hostId);
        doNothing().when(dmeVmwareRalationDao).save(anyList());
        when(vcsdkUtils.attachTag("32131", "3214", "wewat", vCenterInfo)).thenReturn("tesat");

        //通过服务等级创建
        vmfsAccessService.createVmfs(params);

        String requestUnLevel
            = "{\"name\": \"testvmfsWxy001\",\"volumeName\": \"testvmfsWxy\",\"isSameName\": true,\"capacity\": 1,\"capacityUnit\": \"GB\",\"count\": 1,\"service_level_id\": null,\"service_level_name\": \"cctest\",\"version\": \"5\",\"blockSize\": 1024,\"spaceReclamationGranularity\": 1024,\"spaceReclamationPriority\": \"low\",\"host\": \"10.143.133.17\",\"hostId\": \"urn:vmomi:HostSystem:host-1034:674908e5-ab21-4079-9cb1-596358ee5dd1\",\"cluster\": null,\"clusterId\": \"123213213\",\"storage_id\": \"132341\",\"pool_raw_id\": null,\"workload_type_id\": null,\"alloctype\": null,\"control_policy\": null,\"latencyChoose\": false,\"latency\": null,\"maxbandwidth\": null,\"maxbandwidthChoose\": false,\"maxiops\": null,\"maxiopsChoose\": false,\"minbandwidth\": null,\"minbandwidthChoose\": false,\"miniops\": null,\"miniopsChoose\": false,\"qosname\": null,\"deviceName\": null,\"hostDataloadSuccess\": true,\"culDataloadSuccess\": true}";
        Map<String, Object> paramsUnLevel = gson.fromJson(requestUnLevel, Map.class);
        //通过非服务等级创建
        vmfsAccessService.createVmfs(paramsUnLevel);
    }

    @Test
    public void createVmfsToCluster() throws Exception {
        String requestUnLevel = "{\"cluster\": 1123,\"clusterId\": \"123213213\"}";
        String clusterId = "123213213";
        String clusterName = "131";
        Map<String, Object> paramsUnLevel = gson.fromJson(requestUnLevel, Map.class);
        VcConnectionHelpers helper = mock(VcConnectionHelpers.class);
        when(vcsdkUtils.getVcConnectionHelper()).thenReturn(helper);
        ManagedObjectReference mor = mock(ManagedObjectReference.class);
        when(helper.objectId2Mor(clusterId)).thenReturn(mor);
        when(mor.getValue()).thenReturn(clusterName);

        String hostGroupId = "333";
        List<Map<String, Object>> hostgrouplist = new ArrayList<>();
        Map<String, Object> m = new HashMap<>();
        m.put("name", clusterName);
        m.put("id", hostGroupId);
        hostgrouplist.add(m);
        when(dmeAccessService.getDmeHostGroups(clusterName)).thenReturn(hostgrouplist);

        String hostName = "7";
        String hostId = "8";
        List<Map<String, String>> list = new ArrayList<>();
        Map<String, String> mm = new HashMap<>();
        mm.put("hostName", hostName);
        mm.put("hostId", hostId);
        list.add(mm);
        String vmwarehosts = gson.toJson(list);
        when(vcsdkUtils.getHostsOnCluster(clusterId)).thenReturn(vmwarehosts);

        String hbasStr = "[{\"name\":\"iqn.1994-05.com.redhat:b7b236e46d8e2\",\"type\":\"ISCSI\"}]";
        List<Map<String, Object>> hbas = gson.fromJson(hbasStr,
            new TypeToken<List<Map<String, Object>>>() { }.getType());
        when(vcsdkUtils.getHbasByHostObjectId(hostId)).thenReturn(hbas);

        String demHostId = "222";
        String hostListStr
            = "[{\"managed_status\": \"NORMAL\",\"project_id\": \"\",\"ip\": \"10.143.133.118\",\"os_type\": \"LINUX\",\"name\": \"im_1node\",\"overall_status\": \"\",\"initiator_count\": 1,\"id\": \"222\",\"os_status\": \"ONLINE\",\"access_mode\": \"NONE\",\"display_status\": \"NORMAL\"}]";
        List<Map<String, Object>> hostList = gson.fromJson(hostListStr,
            new TypeToken<List<Map<String, Object>>>() { }.getType());
        when(dmeAccessService.getDmeHosts(null)).thenReturn(hostList);

        String initiatorStr
            = "[{\"port_name\":\"iqn.1994-05.com.redhat:b7b236e46d8e\",\"protocol\":\"ISCSI\",\"id\":\"0d922e34-0dc0-45d8-a4c0-5f9135cd9661\",\"status\":\"UNKNOWN\"}]";
        List<Map<String, Object>> initiators = gson.fromJson(initiatorStr,
            new TypeToken<List<Map<String, Object>>>() { }.getType());
        when(dmeAccessService.getDmeHostInitiators(demHostId)).thenReturn(initiators);

        Map<String, Object> params = new HashMap<>();
        List<String> hostlists = new ArrayList<>();
        hostlists.add(demHostId);
        params.put("cluster", clusterName);
        params.put("hostids", hostlists);
        Map<String, Object> hostmap = new HashMap<>(16);
        hostmap.put("id", "234");
        when(dmeAccessService.createHostGroup(params)).thenReturn(hostmap);

        Map<String, Object> params1 = new HashMap<>();
        params1.put("host", hostName);
        params1.put("hostId", hostId);
        Map<String, Object> hostmap1 = new HashMap<>();
        hostmap1.put("id", "44");
        when(dmeAccessService.createHost(params1)).thenReturn(hostmap1);
        try {
            vmfsAccessService.createVmfs(paramsUnLevel);
        }catch (Exception ex){

        }
    }

    @Test
    public void mountVmfs() throws Exception {
        String hostId = "urn:vmomi:HostSystem:host-1034:674908e5-ab21-4079-9cb1-596358ee5dd1";
        String hostIp = "10.143.133.17";
        String hostName = "testhost";
        String clusterObjId = "1231";
        String clusterName = "clusterName";
        String demHostId = "9cbd24b5-fb5b-4ad9-9393-cf05b9b97339";
        when(vcsdkUtils.getHostName(hostId)).thenReturn(hostName);
        when(vcsdkUtils.getClusterName(clusterObjId)).thenReturn(clusterName);

        String hbasStr = "[{\"name\":\"iqn.1994-05.com.redhat:b7b236e46d8e\",\"type\":\"ISCSI\"}]";
        List<Map<String, Object>> hbas = gson.fromJson(hbasStr,
            new TypeToken<List<Map<String, Object>>>() { }.getType());
        when(vcsdkUtils.getHbasByHostObjectId(hostId)).thenReturn(hbas);

        String hostListStr
            = "[{\"managed_status\":\"NORMAL\",\"project_id\":\"\",\"ip\":\"10.143.133.118\",\"os_type\":\"LINUX\",\"name\":\"im_1node\",\"overall_status\":\"\",\"initiator_count\":1,\"id\":\"332eb0e9-5e87-4730-8cdd-67b08972010b\",\"os_status\":\"ONLINE\",\"access_mode\":\"NONE\",\"display_status\":\"NORMAL\"},{\"managed_status\":\"NORMAL\",\"project_id\":\"\",\"ip\":\"10.143.133.18\",\"os_type\":\"LINUX\",\"name\":\"10.143.133.18\",\"overall_status\":\"\",\"initiator_count\":1,\"id\":\"8cedbf3c-a65a-43f7-9366-d9c4d9601623\",\"os_status\":\"ONLINE\",\"access_mode\":\"NONE\",\"display_status\":\"NORMAL\"},{\"managed_status\":\"NORMAL\",\"project_id\":\"\",\"ip\":\"10.143.133.15\",\"os_type\":\"LINUX\",\"name\":\"10.143.133.15\",\"overall_status\":\"\",\"initiator_count\":1,\"id\":\"c3463836-b58a-4a33-815c-d32c52e606d2\",\"os_status\":\"ONLINE\",\"access_mode\":\"NONE\",\"display_status\":\"NORMAL\"},{\"managed_status\":\"NORMAL\",\"project_id\":\"\",\"ip\":\"10.143.133.17\",\"os_type\":\"LINUX\",\"name\":\"10.143.133.17\",\"overall_status\":\"\",\"initiator_count\":1,\"id\":\"9cbd24b5-fb5b-4ad9-9393-cf05b9b97339\",\"os_status\":\"ONLINE\",\"access_mode\":\"NONE\",\"display_status\":\"NORMAL\"},{\"managed_status\":\"NORMAL\",\"hostGroups\":[{\"name\":\"Host003\",\"id\":\"34108dcd-ac32-48ab-8ef8-62c59acefce4\"}],\"project_id\":\"\",\"ip\":\"\",\"os_type\":\"LINUX\",\"name\":\"Host003\",\"overall_status\":\"\",\"initiator_count\":1,\"id\":\"34108dcd-ac32-48ab-8ef8-62c59acefce4\",\"os_status\":\"ONLINE\",\"access_mode\":\"NONE\",\"display_status\":\"NORMAL\"},{\"managed_status\":\"NORMAL\",\"hostGroups\":[{\"name\":\"10.143.133.196\",\"id\":\"4f987715-19d5-4d63-85e4-b672e814705b\"},{\"name\":\"10.143.133.196\",\"id\":\"4f987715-19d5-4d63-85e4-b672e814705b\"}],\"project_id\":\"\",\"ip\":\"10.143.133.196\",\"os_type\":\"LINUX\",\"name\":\"10.143.133.196\",\"overall_status\":\"\",\"initiator_count\":1,\"id\":\"4f987715-19d5-4d63-85e4-b672e814705b\",\"os_status\":\"ONLINE\",\"access_mode\":\"NONE\",\"display_status\":\"NORMAL\"}]";
        List<Map<String, Object>> hostList = gson.fromJson(hostListStr,
            new TypeToken<List<Map<String, Object>>>() { }.getType());
        when(dmeAccessService.getDmeHosts(null)).thenReturn(hostList);

        String initiatorStr
            = "[{\"port_name\":\"iqn.1994-05.com.redhat:b7b236e46d8e\",\"protocol\":\"ISCSI\",\"id\":\"0d922e34-0dc0-45d8-a4c0-5f9135cd9661\",\"status\":\"UNKNOWN\"}]";
        List<Map<String, Object>> initiators = gson.fromJson(initiatorStr,
            new TypeToken<List<Map<String, Object>>>() { }.getType());
        when(dmeAccessService.getDmeHostInitiators(demHostId)).thenReturn(initiators);

        String hostMapStr = "";
        Map<String, Object> hostMap = gson.fromJson(hostMapStr, new TypeToken<Map<String, Object>>() { }.getType());
        Map<String, Object> param = new HashMap<>();
        param.put("host", hostIp);
        param.put("hostId", hostId);
        when(dmeAccessService.createHost(param)).thenReturn(hostMap);

        Map mountParams = new HashMap();
        List<String> volumeIds = new ArrayList<>();
        volumeIds.add("123");
        mountParams.put("hostId", hostId);
        mountParams.put("volumeIds", volumeIds);
        List<String> dataStoreNames = new ArrayList<>();
        String dataStoreName = "dataStoreTest";
        dataStoreNames.add(dataStoreName);
        mountParams.put("dataStoreNames", dataStoreNames);

        String url = "/rest/blockservice/v1/volumes/host-mapping";
        String s = "{\"task_id\":\"1231123213\"}";
        Map requestbody = new HashMap<>();
        requestbody.put("volume_ids", volumeIds);
        requestbody.put("host_id", demHostId);
        ResponseEntity responseEntity = new ResponseEntity<>(s, null, HttpStatus.ACCEPTED);
        when(dmeAccessService.access(url, HttpMethod.POST, gson.toJson(requestbody))).thenReturn(responseEntity);
        List<String> taskIds = new ArrayList<>();
        String taskId = "1231123213";
        taskIds.add(taskId);
        when(taskService.checkTaskStatus(taskIds)).thenReturn(true);
        doNothing().when(vcsdkUtils).scanDataStore(null, hostId);
        Map<String, Object> dsmap = new HashMap<>();
        dsmap.put("name", dataStoreName);
        doNothing().when(vcsdkUtils).mountVmfsOnCluster(gson.toJson(dsmap), null, hostId);

        vmfsAccessService.mountVmfs(mountParams);

    }

    @Test
    public void unmountVmfs() throws Exception {
        Map<String, Object> params = new HashMap<>();
        String dataStoreObjectId = "qq";
        String hostId = "13213";
        String volumeId = "41513";
        List<String> list = new ArrayList();
        list.add(dataStoreObjectId);
        params.put("hostId", hostId);
        List<String> volumeIds = new ArrayList();
        volumeIds.add(volumeId);
        params.put(DmeConstants.VOLUMEIDS, volumeIds);
        params.put(DmeConstants.DATASTOREOBJECTIDS, list);
        unmountVmfBefore();

        vmfsAccessService.unmountVmfs(params);
    }

    void unmountVmfBefore() throws Exception {
        Map<String, Object> params = new HashMap<>();
        String dataStoreObjectId = "qq";
        String hostId = "13213";
        String volumeId = "41513";
        List<String> list = new ArrayList();
        list.add(dataStoreObjectId);
        params.put("hostId", hostId);
        List<String> volumeIds = new ArrayList();
        volumeIds.add(volumeId);
        params.put(DmeConstants.VOLUMEIDS, volumeIds);
        params.put(DmeConstants.DATASTOREOBJECTIDS, list);
        DmeVmwareRelation dvr = new DmeVmwareRelation();
        String dataStoreName = "dataStoreName13";
        String dataStoreId = "123";
        dvr.setVolumeId(volumeId);
        dvr.setStoreName(dataStoreName);
        when(dmeVmwareRalationDao.getDmeVmwareRelationByDsId(dataStoreObjectId)).thenReturn(dvr);
        Map<String, Object> hbaMap = new HashMap<>();
        hbaMap.put("name", "112");
        when(vcsdkUtils.getHbaByHostObjectId(hostId)).thenReturn(hbaMap);

        String hostGroupid = "123123";
        String hostGroupName = "123123Test";
        String url = "/rest/blockservice/v1/volumes/" + volumeId;
        String jsonData = "{\n" + "    \"volume\": {\n" + "        \"attachments\": [\n" + "            {\n"
            + "                \"id\": \"8b561dd2-03bb-4f20-98c4-8092e75fe951\",\n"
            + "                \"volume_id\": \"955a0632-c309-4471-a116-6a059d84ade3\",\n"
            + "                \"host_id\": \"9cbd24b5-fb5b-4ad9-9393-cf05b9b97339\",\n"
            + "                \"attached_at\": \"2020-10-26T06:50:20.000000\",\n"
            + "                \"attached_host_group\": \"123123\"\n" + "            }\n" + "        ]\n" + "    }\n"
            + "}";
        ResponseEntity<String> responseEntity = new ResponseEntity<>(jsonData, null, HttpStatus.OK);
        when(dmeAccessService.access(url, HttpMethod.GET, null)).thenReturn(responseEntity);
        Map hostGroupMap = new HashMap();
        hostGroupMap.put("name", hostGroupName);
        when(dmeAccessService.getDmeHostGroup(hostGroupid)).thenReturn(hostGroupMap);

        String hbasStr = "[{\"name\":\"112\",\"type\":\"ISCSI\"}]";
        List<Map<String, Object>> hostHbas = gson.fromJson(hbasStr,
            new TypeToken<List<Map<String, Object>>>() { }.getType());
        when(vcsdkUtils.getHbasByHostObjectId(hostId)).thenReturn(hostHbas);

        String hostListStr
            = "[{\"managed_status\": \"NORMAL\",\"project_id\": \"\",\"ip\": \"10.143.133.118\",\"os_type\": \"LINUX\",\"name\": \"im_1node\",\"overall_status\": \"\",\"initiator_count\": 1,\"id\": \"13213\",\"os_status\": \"ONLINE\",\"access_mode\": \"NONE\",\"display_status\": \"NORMAL\"}]";
        List<Map<String, Object>> hostList = gson.fromJson(hostListStr,
            new TypeToken<List<Map<String, Object>>>() { }.getType());
        when(dmeAccessService.getDmeHosts(null)).thenReturn(hostList);

        String initiatorStr
            = "[{\"port_name\":\"112\",\"protocol\":\"ISCSI\",\"id\":\"0d922e34-0dc0-45d8-a4c0-5f9135cd9661\",\"status\":\"UNKNOWN\"}]";
        List<Map<String, Object>> initiators = gson.fromJson(initiatorStr,
            new TypeToken<List<Map<String, Object>>>() { }.getType());
        when(dmeAccessService.getDmeHostInitiators(hostId)).thenReturn(initiators);

        String deleteVolUrl = "/rest/blockservice/v1/volumes/delete";
        Map<String, Object> requestbody1 = new HashMap<>();
        requestbody1.put("volume_ids", params.get(DmeConstants.VOLUMEIDS));
        String taskStr = "{\"task_id\":\"1231123213\"}";
        ResponseEntity responseEntity2 = new ResponseEntity(taskStr, null, HttpStatus.OK);
        dmeAccessService.access(deleteVolUrl, HttpMethod.POST, gson.toJson(responseEntity2));

        Map<String, String> mappeddmegroups = new HashMap<>();
        mappeddmegroups.put(hostGroupName, "has");
        List<Map<String, String>> clusters = new ArrayList<>();
        Map<String, String> map = new HashMap<>();
        String clusterId = "clusterId";
        String clusterName = "clusterName";
        map.put("clusterId", clusterId);
        map.put("clusterName", clusterName);
        clusters.add(map);
        String listStr1 = gson.toJson(clusters);
        when(vcsdkUtils.getMountClustersByDsObjectId(dataStoreId, mappeddmegroups)).thenReturn(listStr1);
        String unmappingUrl = "/rest/blockservice/v1/volumes/host-unmapping";
        Map<String, Object> requestbody = new HashMap<>();
        requestbody.put("host_id", hostId);
        requestbody.put("volume_ids", volumeIds);
        ResponseEntity responseEntity1 = new ResponseEntity(taskStr, null, HttpStatus.ACCEPTED);
        when(dmeAccessService.access(unmappingUrl, HttpMethod.POST, gson.toJson(requestbody))).thenReturn(
            responseEntity1);
        List<String> taskIds = new ArrayList<>();
        String taskId = "1231123213";
        taskIds.add(taskId);
        when(taskService.checkTaskStatus(taskIds)).thenReturn(true);

        List<Map<String, String>> hostList1 = new ArrayList<>();
        Map<String, String> hostMap = new HashMap<>();
        hostMap.put("hostId", hostId);
        hostMap.put("hostName", "12321");
        hostList1.add(hostMap);
        String listStr = gson.toJson(hostList1);
        when(vcsdkUtils.getHostsByDsObjectId(dataStoreObjectId, true)).thenReturn(listStr);
    }

    @Test
    public void deleteVmfs() throws Exception {
        Map<String, Object> params = new HashMap<>();
        String dataStoreObjectId = "qq";
        String dataStoreObjectId2 = "qq1";
        String hostId = "13213";
        String volumeId = "41513";
        List<String> list = new ArrayList();
        list.add(dataStoreObjectId);
        list.add(dataStoreObjectId2);
        params.put("hostId", hostId);
        List<String> volumeIds = new ArrayList();
        volumeIds.add(volumeId);
        params.put(DmeConstants.VOLUMEIDS, volumeIds);
        params.put(DmeConstants.DATASTOREOBJECTIDS, list);
        when(vcsdkUtils.hasVmOnDatastore(dataStoreObjectId2)).thenReturn(true);
        DmeVmwareRelation dvr = new DmeVmwareRelation();
        dvr.setVolumeId(volumeId);
        dvr.setStoreName("1111");
        when(dmeVmwareRalationDao.getDmeVmwareRelationByDsId(dataStoreObjectId)).thenReturn(dvr);
        unmountVmfBefore();
        String VOLUME_DELETE = "/rest/blockservice/v1/volumes/delete";
        Map<String, Object> requestbody = new HashMap<>();
        requestbody.put("volume_ids", volumeIds);
        String taskStr = "{\"task_id\":\"1231123213\"}";
        ResponseEntity responseEntity2 = new ResponseEntity(taskStr, null, HttpStatus.ACCEPTED);
        when(dmeAccessService.access(VOLUME_DELETE, HttpMethod.POST, gson.toJson(requestbody))).thenReturn(
            responseEntity2);

        vmfsAccessService.deleteVmfs(params);
    }

    @Test
    public void volumeDetail() throws Exception {
        String storageObjectId = "a11";
        String volumeId = "1122";
        List<String> volumeIds = new ArrayList<>();
        volumeIds.add(volumeId);
        when(dmeVmwareRalationDao.getVolumeIdsByStorageId(storageObjectId)).thenReturn(volumeIds);
        String url = "/rest/blockservice/v1/volumes" + "/" + volumeId;
        String s
            = "{\"volume\":{\"id\":\"955a0632-c309-4471-a116-6a059d84ade3\",\"name\":\"VMFSTest20201026\",\"description\":null,\"status\":\"normal\",\"attached\":true,\"project_id\":null,\"alloctype\":\"thick\",\"capacity\":1,\"service_level_name\":\"cctest\",\"attachments\":[{\"id\":\"8b561dd2-03bb-4f20-98c4-8092e75fe951\",\"volume_id\":\"955a0632-c309-4471-a116-6a059d84ade3\",\"host_id\":\"9cbd24b5-fb5b-4ad9-9393-cf05b9b97339\",\"attached_at\":\"2020-10-26T06:50:20.000000\",\"attached_host_group\":null}],\"volume_raw_id\":\"174\",\"volume_wwn\":\"67c1cf11005893452a7c7314000000ae\",\"storage_id\":\"b94bff9d-0dfb-11eb-bd3d-0050568491c9\",\"storage_sn\":\"2102351QLH9WK5800028\",\"pool_raw_id\":\"0\",\"capacity_usage\":null,\"protected\":false,\"updated_at\":\"2020-10-26T06:50:20.000000\",\"created_at\":\"2020-10-26T06:50:15.000000\",\"tuning\":{\"smarttier\":\"0\",\"dedupe_enabled\":null,\"compression_enabled\":null,\"workload_type_id\":null,\"smartqos\":{\t\t\t\t\"control_policy\":\"OA\"\t\t\t},\"alloctype\":\"thick\"},\"initial_distribute_policy\":\"0\",\"prefetch_policy\":\"3\",\"owner_controller\":\"0B\",\"prefetch_value\":\"0\"}}";
        ResponseEntity<String> responseEntity = new ResponseEntity<>(s, null, HttpStatus.OK);
        when(dmeAccessService.access(url, HttpMethod.GET, null)).thenReturn(responseEntity);
        s = "{\"name\":\"test\"}";
        url = DmeConstants.DME_STORAGE_DETAIL_URL.replace("{storage_id}","b94bff9d-0dfb-11eb-bd3d-0050568491c9");
        responseEntity = new ResponseEntity<>(s, null, HttpStatus.OK);
        when(dmeAccessService.access(url, HttpMethod.GET, null)).thenReturn(responseEntity);
        url = String.format(DmeConstants.DME_RESOURCE_INSTANCE_LIST, "SYS_StoragePool");
        //构造查询body
        String constraint = String.format("{\"constraint\": [{\"simple\": {\"name\": \"poolId\",\"value\": \"%s\"}}]}",
            "0");
        url = url + "?condition={json}";
        s = "{\"objList\": [{\"name\": \"aa\"}],\"totalNum\": 1}";
        responseEntity = new ResponseEntity<>(s, null, HttpStatus.OK);
        when(dmeAccessService.accessByJson(url, HttpMethod.GET, constraint)).thenReturn(responseEntity);

        vmfsAccessService.volumeDetail(storageObjectId);
    }

    @Test
    public void scanVmfs() throws Exception {
        String storeObjectId = "12321";
        String volumeWwn = "12321123";
        String storeObjectName = "testDataStore";
        List<Map<String, Object>> lists = new ArrayList<>();
        Map<String, Object> dsmap = new HashMap<>();
        dsmap.put("objectid", storeObjectId);
        dsmap.put("name", storeObjectName);
        dsmap.put("capacity", 4.75);
        dsmap.put("freeSpace", 4.052734375);
        dsmap.put("uncommitted", 4.15);
        List<String> wwnList = new ArrayList(16);
        wwnList.add(volumeWwn);
        dsmap.put("vmfsWwnList", wwnList);
        lists.add(dsmap);
        String listStr = gson.toJson(lists);
        when(vcsdkUtils.getAllVmfsDataStoreInfos(DmeConstants.STORE_TYPE_VMFS)).thenReturn(listStr);
        String dataJson
            = "{\"volumes\":[{\"id\":\"8f6d93f1-4214-46bc-ae7a-85f8349ebbd2\",\"name\":\"Drdm17\",\"description\":null,\"status\":\"normal\",\"attached\":true,\"project_id\":null,\"alloctype\":\"thick\",\"capacity\":1,\"service_level_name\":\"lgqtest\",\"attachments\":[{\"id\":\"caa34ee1-e935-4958-b106-022d7beef447\",\"volume_id\":\"8f6d93f1-4214-46bc-ae7a-85f8349ebbd2\",\"host_id\":\"9cbd24b5-fb5b-4ad9-9393-cf05b9b97339\",\"attached_at\":\"2020-11-12T02:27:40.000000\",\"attached_host_group\":null}],\"volume_raw_id\":\"323\",\"volume_wwn\":\"67c1cf1100589345402376ae00000143\",\"storage_id\":\"b94bff9d-0dfb-11eb-bd3d-0050568491c9\",\"storage_sn\":\"2102351QLH9WK5800028\",\"pool_raw_id\":\"0\",\"capacity_usage\":null,\"protected\":false,\"updated_at\":\"2020-11-12T02:27:40.000000\",\"created_at\":\"2020-11-12T02:27:34.000000\"}],\"count\":25}";
        ResponseEntity<String> responseEntity = new ResponseEntity(dataJson, null, HttpStatus.OK);
        String volumeUrlByName = "/rest/blockservice/v1/volumes" + "?volume_wwn=" + volumeWwn;
        when(dmeAccessService.access(volumeUrlByName, HttpMethod.GET, null)).thenReturn(responseEntity);
        List<String> localWwns = new ArrayList<>(16);
        localWwns.add("333");
        when(dmeVmwareRalationDao.getAllWwnByType(DmeConstants.STORE_TYPE_VMFS)).thenReturn(localWwns);
        doNothing().when(dmeVmwareRalationDao).deleteByWwn(localWwns);
        vmfsAccessService.scanVmfs();
    }

    @Test
    public void getHostsByStorageId() throws Exception {
        String storageId = "123123";
        String volumeId = "1231";
        String dmeHostId = "1232";
        String hostId = "1232";
        List<Map<String, String>> hostList = new ArrayList<>();
        Map<String, String> hostMap = new HashMap<>();
        hostMap.put("hostId", hostId);
        hostMap.put("hostName", "12321");
        hostList.add(hostMap);
        String listStr = gson.toJson(hostList);
        when(vcsdkUtils.getHostsByDsObjectId(storageId, true)).thenReturn(listStr);

        DmeVmwareRelation dvr = new DmeVmwareRelation();
        dvr.setVolumeId(volumeId);
        when(dmeVmwareRalationDao.getDmeVmwareRelationByDsId(storageId)).thenReturn(dvr);
        String volumeDetailUrl = "/rest/blockservice/v1/volumes/" + volumeId;
        String jsonData
            = "{\"volume\":{\"attachments\":[{\"id\":\"8b561dd2-03bb-4f20-98c4-8092e75fe951\",\"volume_id\":\"955a0632-c309-4471-a116-6a059d84ade3\",\"host_id\":\"9cbd24b5-fb5b-4ad9-9393-cf05b9b97339\",\"attached_at\":\"2020-10-26T06:50:20.000000\",\"attached_host_group\":\"123123\"}]}}";
        ResponseEntity<String> responseEntity = new ResponseEntity<>(jsonData, null, HttpStatus.OK);
        when(dmeAccessService.access(volumeDetailUrl, HttpMethod.GET, null)).thenReturn(responseEntity);
        String hostgroupid = "123123";
        Map<String, Object> hostgroupmap = new HashMap<>();
        String groupName = "tttt";
        hostgroupmap.put("name", groupName);
        when(dmeAccessService.getDmeHostGroup(hostgroupid)).thenReturn(hostgroupmap);
        Map<String, String> mappeddmegroups = new HashMap<>();
        mappeddmegroups.put(groupName, "has");
        List<Map<String, String>> clusters = new ArrayList<>();
        Map<String, String> map = new HashMap<>();
        String clusterId = "clusterId";
        String clusterName = "clusterName";
        map.put("clusterId", clusterId);
        map.put("clusterName", clusterName);
        clusters.add(map);
        String listStr1 = gson.toJson(clusters);
        when(vcsdkUtils.getMountClustersByDsObjectId(storageId, mappeddmegroups)).thenReturn(listStr1);
        VcConnectionHelpers helper = mock(VcConnectionHelpers.class);
        when(vcsdkUtils.getVcConnectionHelper()).thenReturn(helper);
        ManagedObjectReference mor = mock(ManagedObjectReference.class);
        when(helper.objectId2Mor(clusterId)).thenReturn(mor);
        when(mor.getValue()).thenReturn(clusterName);

        List<Map<String, Object>> hostgrouplist = new ArrayList<>();
        Map<String, Object> hostgroupMap = new HashMap<>();
        hostgroupMap.put("name", clusterName);
        hostgroupMap.put("id", clusterId);
        hostgrouplist.add(hostgroupMap);
        when(dmeAccessService.getDmeHostGroups(clusterName)).thenReturn(hostgrouplist);

        String hbasStr = "[{\"name\":\"iqn.1994-05.com.redhat:b7b236e46d8e\",\"type\":\"ISCSI\"}]";
        List<Map<String, Object>> hostHbas = gson.fromJson(hbasStr,
            new TypeToken<List<Map<String, Object>>>() { }.getType());
        when(vcsdkUtils.getHbasByHostObjectId(hostId)).thenReturn(hostHbas);

        List<Map<String, Object>> hbas = new ArrayList<>();
        Map<String, Object> hbaMap = new HashMap<>();
        hbaMap.put("name", clusterName);
        hbas.add(hbaMap);
        when(vcsdkUtils.getHbasByClusterObjectId(clusterId)).thenReturn(hbas);
        List<Map<String, Object>> dmehosts = new ArrayList<>();
        Map<String, Object> dmehostMap = new HashMap<>();
        dmehostMap.put("id", dmeHostId);
        dmehosts.add(dmehostMap);
        when(dmeAccessService.getDmeHostInHostGroup(clusterId)).thenReturn(dmehosts);

        List<Map<String, Object>> subinitiators = new ArrayList<>();
        Map<String, Object> subinitiatorMap = new HashMap<>(16);
        subinitiatorMap.put("id", "11123");
        subinitiatorMap.put("port_name", clusterName);
        subinitiatorMap.put("status", "normarl");
        subinitiatorMap.put("protocol", "ipv4");
        subinitiators.add(subinitiatorMap);
        when(dmeAccessService.getDmeHostInitiators(dmeHostId)).thenReturn(subinitiators);

        List<Map<String, String>> vmwareHostList = new ArrayList<>();
        Map<String, String> vmwareHostMap = new HashMap<>();
        vmwareHostMap.put("hostId", dmeHostId);
        vmwareHostList.add(vmwareHostMap);
        String vmwarehosts = gson.toJson(vmwareHostList);
        when(vcsdkUtils.getHostsOnCluster(clusterId)).thenReturn(vmwarehosts);

        vmfsAccessService.getHostsByStorageId(storageId);
    }

    @Test
    public void getHostGroupsByStorageId() throws Exception {
        String storageId = "11";
        String volumeId = "123";
        String hostgroupid = "1231";
        String hostgroupName = "xxx";
        DmeVmwareRelation dvr = new DmeVmwareRelation();
        dvr.setVolumeId(volumeId);
        when(dmeVmwareRalationDao.getDmeVmwareRelationByDsId(storageId)).thenReturn(dvr);
        String url = "/rest/blockservice/v1/volumes/" + volumeId;
        String s = "{\"volume\":{\"attachments\":[{\"attached_host_group\":\"12321\"}]}}";
        ResponseEntity<String> responseEntity = new ResponseEntity<>(s, null, HttpStatus.OK);
        when(dmeAccessService.access(url, HttpMethod.GET, null)).thenReturn(responseEntity);

        Map<String, Object> hostgroupmap = new HashMap<>();
        hostgroupmap.put("name", hostgroupName);
        when(dmeAccessService.getDmeHostGroup(hostgroupid)).thenReturn(hostgroupmap);
        Map<String, String> mappeddmegroups = new HashMap<>();
        mappeddmegroups.put(String.valueOf(hostgroupmap.get("name")), "has");
        List<Map<String, String>> list = new ArrayList<>(16);
        Map<String, String> map = new HashMap<>(16);
        String clusterId = "aa";
        String clusterName = "bb";
        map.put("clusterId", clusterId);
        map.put("clusterName", clusterName);
        list.add(map);
        String listStr = gson.toJson(list);
        when(vcsdkUtils.getMountClustersByDsObjectId(storageId, mappeddmegroups)).thenReturn(listStr);
        VcConnectionHelpers helper = mock(VcConnectionHelpers.class);
        when(vcsdkUtils.getVcConnectionHelper()).thenReturn(helper);
        ManagedObjectReference mor = mock(ManagedObjectReference.class);
        when(helper.objectId2Mor(clusterId)).thenReturn(mor);
        when(mor.getValue()).thenReturn(clusterName);
        List<Map<String, Object>> hostgrouplist = new ArrayList<>();
        Map<String, Object> hostgroupMap = new HashMap<>();
        hostgroupMap.put("name", clusterName);
        hostgroupMap.put("id", clusterId);
        hostgrouplist.add(hostgroupMap);
        when(dmeAccessService.getDmeHostGroups(clusterName)).thenReturn(hostgrouplist);


        vmfsAccessService.getHostGroupsByStorageId(storageId);
    }

    @Test
    public void queryVmfs() throws Exception {
        String dataStoreObjectId = "11";
        String volumeId = "123";
        List<DmeVmwareRelation> dvrlist = new ArrayList<>();
        DmeVmwareRelation dvr = new DmeVmwareRelation();
        dvr.setStoreId(dataStoreObjectId);
        dvr.setVolumeId(volumeId);
        dvrlist.add(dvr);
        when(dmeVmwareRalationDao.getDmeVmwareRelation(DmeConstants.STORE_TYPE_VMFS)).thenReturn(dvrlist);
        List<Storage> storagemap = new ArrayList<>(16);
        Storage storage = new Storage();
        storage.setId("112");
        storage.setName("tasts");
        storagemap.add(storage);
        when(dmeStorageService.getStorages()).thenReturn(storagemap);
        String listStr
            = "[{\"objectid\": \"11\",\"capacity\": 123123141415,\"freeSpace\": 123123141415,\"uncommitted\": 123123141415,\"name\": \"11\"}]";
        when(vcsdkUtils.getAllVmfsDataStoreInfos(DmeConstants.STORE_TYPE_VMFS)).thenReturn(listStr);
        String detailedVolumeUrl = "/rest/blockservice/v1/volumes" + "/" + volumeId;
        String s
            = "{\"volume\":{\"id\":\"955a0632-c309-4471-a116-6a059d84ade3\",\"name\":\"VMFSTest20201026\",\"description\":null,\"status\":\"normal\",\"attached\":true,\"project_id\":null,\"alloctype\":\"thick\",\"capacity\":1,\"service_level_name\":\"cctest\",\"attachments\":[{\"id\":\"8b561dd2-03bb-4f20-98c4-8092e75fe951\",\"volume_id\":\"955a0632-c309-4471-a116-6a059d84ade3\",\"host_id\":\"9cbd24b5-fb5b-4ad9-9393-cf05b9b97339\",\"attached_at\":\"2020-10-26T06:50:20.000000\",\"attached_host_group\":null}],\"volume_raw_id\":\"174\",\"volume_wwn\":\"67c1cf11005893452a7c7314000000ae\",\"storage_id\":\"b94bff9d-0dfb-11eb-bd3d-0050568491c9\",\"storage_sn\":\"2102351QLH9WK5800028\",\"pool_raw_id\":\"0\",\"capacity_usage\":null,\"protected\":false,\"updated_at\":\"2020-10-26T06:50:20.000000\",\"created_at\":\"2020-10-26T06:50:15.000000\",\"tuning\":{\"smarttier\":\"0\",\"dedupe_enabled\":null,\"compression_enabled\":null,\"workload_type_id\":null,\"smartqos\":{\t\t\t\t\"control_policy\":\"OA\"\t\t\t},\"alloctype\":\"thick\"},\"initial_distribute_policy\":\"0\",\"prefetch_policy\":\"3\",\"owner_controller\":\"0B\",\"prefetch_value\":\"0\"}}";
        ResponseEntity<String> responseEntity = new ResponseEntity<>(s, null, HttpStatus.OK);
        when(dmeAccessService.access(detailedVolumeUrl, HttpMethod.GET, null)).thenReturn(responseEntity);

        vmfsAccessService.queryVmfs(dataStoreObjectId);
    }

    @Test
    public void queryDatastoreByName() throws Exception {
        String name1 = "1";
        String name2 = "2";
        when(dmeVmwareRalationDao.getDataStoreByName(name1)).thenReturn("aa");
        when(dmeVmwareRalationDao.getDataStoreByName(name2)).thenReturn("2");
        vmfsAccessService.queryDatastoreByName(name1);
        vmfsAccessService.queryDatastoreByName(name2);
    }

    @Test
    public void checkOrCreateToHost() {
    }
}