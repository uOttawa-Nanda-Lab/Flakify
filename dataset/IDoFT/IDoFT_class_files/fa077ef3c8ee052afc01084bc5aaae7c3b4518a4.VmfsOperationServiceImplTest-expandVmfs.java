package com.huawei.dmestore.services;

import com.huawei.dmestore.exception.DmeException;
import com.huawei.dmestore.utils.VCSDKUtils;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

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

import static org.mockito.Mockito.when;

/**
 * @author lianq
 * @className VmfsOperationServiceImplTest
 * @description TODO
 * @date 2020/11/17 10:22
 */
public class VmfsOperationServiceImplTest {

    private Gson gson=new Gson();
    private String url;
    @Mock
    private DmeAccessService dmeAccessService;
    @Mock
    private VCSDKUtils vcsdkUtils;
    @Mock
    private TaskService taskService;

    @InjectMocks
    private VmfsOperationService vmfsOperationService = new VmfsOperationServiceImpl();

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void updateVmfs() throws DmeException {
        String param = "{\n" +
                "    \"name\": \"Dvmfs999\", \n" +
                "    \"isSameName\": true, \n" +
                "    \"volumeId\": \"d6a20f27-fe4c-4a40-ac28-529aad1a7550\", \n" +
                "    \"control_policy\": \"\", \n" +
                "    \"max_iops\": null, \n" +
                "    \"maxiopsChoose\": false, \n" +
                "    \"max_bandwidth\": null, \n" +
                "    \"maxbandwidthChoose\": false, \n" +
                "    \"newVoName\": \"Dvmfs999\", \n" +
                "    \"oldDsName\": \"Dvmfs99\", \n" +
                "    \"newDsName\": \"Dvmfs999\", \n" +
                "    \"min_iops\": null, \n" +
                "    \"miniopsChoose\": false, \n" +
                "    \"min_bandwidth\": null, \n" +
                "    \"minbandwidthChoose\": false, \n" +
                "    \"dataStoreObjectId\": \"urn:vmomi:Datastore:datastore-1233:674908e5-ab21-4079-9cb1-596358ee5dd1\", \n" +
                "    \"service_level_name\": \"lgqtest\", \n" +
                "    \"latency\": null, \n" +
                "    \"latencyChoose\": false\n" +
                "}";
        Map<String, Object> map = gson.fromJson(param, Map.class);
        when(vcsdkUtils.renameDataStore("Dvmfs999","urn:vmomi:Datastore:datastore-1233:674908e5-ab21-4079-9cb1-596358ee5dd1")).thenReturn("success");
        url = "/rest/blockservice/v1/volumes/d6a20f27-fe4c-4a40-ac28-529aad1a7550";
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("task_id", "123");
        ResponseEntity<String> responseEntity = new ResponseEntity<>(gson.toJson(jsonObject), null, HttpStatus.ACCEPTED);
        when(dmeAccessService.access(url, HttpMethod.PUT, "{\"volume\":{\"name\":\"Dvmfs999\"}}")).thenReturn(responseEntity);
        List<String> list = new ArrayList<>();
        list.add("123");
        when(taskService.checkTaskStatus(list)).thenReturn(true);
        vmfsOperationService.updateVmfs("d6a20f27-fe4c-4a40-ac28-529aad1a7550", map);

    }

    @Test
    public void expandVmfs() throws DmeException {
        String param = "{\n" +
                "    \"vo_add_capacity\": \"2\", \n" +
                "    \"capacityUnit\": \"GB\", \n" +
                "    \"volume_id\": \"d6a20f27-fe4c-4a40-ac28-529aad1a7550\", \n" +
                "    \"ds_name\": \"Dvmfs999\", \n" +
                "    \"obj_id\": \"urn:vmomi:Datastore:datastore-1233:674908e5-ab21-4079-9cb1-596358ee5dd1\"\n" +
                "}";
        Map<String,String> map = gson.fromJson(param, Map.class);
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("task_id", "123");
        ResponseEntity<String> responseEntity = new ResponseEntity<>(gson.toJson(jsonObject), null, HttpStatus.ACCEPTED);
        String param2 = "{\"volumes\":[{\"volume_id\":\"d6a20f27-fe4c-4a40-ac28-529aad1a7550\",\"added_capacity\":2}]}";
        when(dmeAccessService.access("/rest/blockservice/v1/volumes/expand", HttpMethod.POST, param2)).thenReturn(responseEntity);
        List<String> list = new ArrayList<>();
        list.add("123");
        when(taskService.checkTaskStatus(list)).thenReturn(true);
        when(vcsdkUtils.expandVmfsDatastore("Dvmfs999", 2, "urn:vmomi:Datastore:datastore-1233:674908e5-ab21-4079-9cb1-596358ee5dd1")).thenReturn("success");
        vmfsOperationService.expandVmfs(map);

    }

    @Test
    public void recycleVmfsCapacity() throws DmeException {
        List<String> list = new ArrayList<>();
        list.add("Dvmfs999");
        when(vcsdkUtils.recycleVmfsCapacity("Dvmfs999")).thenReturn("success");
        vmfsOperationService.recycleVmfsCapacity(list);

    }

    @Test
    public void recycleVmfsCapacityByDataStoreIds() throws DmeException {
        List<String> list = new ArrayList<>();
        list.add("Dvmfs999");
        when(vcsdkUtils.getDataStoreName("Dvmfs999")).thenReturn("success");
        when(vcsdkUtils.recycleVmfsCapacity("success")).thenReturn("success");
        vmfsOperationService.recycleVmfsCapacityByDataStoreIds(list);

    }

    @Test
    public void updateVmfsServiceLevel() throws DmeException {
        String param = "{\n" +
                "    \"vo_add_capacity\": 2, \n" +
                "    \"capacityUnit\": \"GB\", \n" +
                "    \"volume_id\": \"d6a20f27-fe4c-4a40-ac28-529aad1a7550\", \n" +
                "    \"ds_name\": \"Dvmfs999\", \n" +
                "    \"obj_id\": \"urn:vmomi:Datastore:datastore-1233:674908e5-ab21-4079-9cb1-596358ee5dd1\"\n" +
                "}";
        Map<String, Object> map = gson.fromJson(param, Map.class);
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("task_id", "123");
        ResponseEntity<String> responseEntity = new ResponseEntity<>(gson.toJson(jsonObject), null, HttpStatus.ACCEPTED);
        when(dmeAccessService.access("/rest/blockservice/v1/volumes/update-service-level", HttpMethod.POST, "{}")).thenReturn(responseEntity);
        List<String> list = new ArrayList<>();
        list.add("123");
        when(taskService.checkTaskStatus(list)).thenReturn(true);
        vmfsOperationService.updateVmfsServiceLevel(map);

    }

    @Test
    public void listServiceLevelVmfs() throws DmeException {
        String param = "{\"vo_add_capacity\":2.0,\"capacityUnit\":\"GB\",\"volume_id\":\"d6a20f27-fe4c-4a40-ac28-529aad1a7550\",\"ds_name\":\"Dvmfs999\",\"obj_id\":\"urn:vmomi:Datastore:datastore-1233:674908e5-ab21-4079-9cb1-596358ee5dd1\"}";
        Map<String, Object> map = gson.fromJson(param, Map.class);
        String resp = " {\n" +
                "    \"id\": \"354a2dec-5d84-4e66-afc5-f3564f087c3f\", \n" +
                "    \"name\": \"D新策略-3\", \n" +
                "    \"description\": \"block service-level for dj\", \n" +
                "    \"type\": \"BLOCK\", \n" +
                "    \"protocol\": null, \n" +
                "    \"total_capacity\": 0, \n" +
                "    \"used_capacity\": 0, \n" +
                "    \"free_capacity\": 0, \n" +
                "    \"capabilities\": {\n" +
                "        \"resource_type\": \"thin\", \n" +
                "        \"compression\": null, \n" +
                "        \"deduplication\": null, \n" +
                "        \"iopriority\": null, \n" +
                "        \"smarttier\": null, \n" +
                "        \"qos\": {\n" +
                "            \"qos_param\": {\n" +
                "                \"latency\": null, \n" +
                "                \"latencyUnit\": \"ms\", \n" +
                "                \"minBandWidth\": null, \n" +
                "                \"minIOPS\": null, \n" +
                "                \"maxBandWidth\": 2333, \n" +
                "                \"maxIOPS\": null\n" +
                "            }, \n" +
                "            \"enabled\": true\n" +
                "        }\n" +
                "    }\n" +
                "}\n";
        JsonObject asJsonObject = new JsonParser().parse(resp).getAsJsonObject();
        List<Object> list = new ArrayList<>();
        list.add(asJsonObject);
        Map<String, Object> map1 = new HashMap<>();
        map1.put("service-levels", list);
        ResponseEntity<String> responseEntity = new ResponseEntity<>(gson.toJson(map1), null, HttpStatus.OK);
        when(dmeAccessService.access("/rest/service-policy/v1/service-levels", HttpMethod.GET, param)).thenReturn(responseEntity);
        vmfsOperationService.listServiceLevelVmfs(map);
    }
}