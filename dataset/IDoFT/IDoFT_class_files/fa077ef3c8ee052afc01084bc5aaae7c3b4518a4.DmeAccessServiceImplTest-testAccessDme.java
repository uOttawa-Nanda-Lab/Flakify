package com.huawei.dmestore.services;

import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.huawei.dmestore.dao.DmeInfoDao;
import com.huawei.dmestore.dao.ScheduleDao;
import com.huawei.dmestore.entity.DmeInfo;
import com.huawei.dmestore.task.ScheduleSetting;
import com.huawei.dmestore.utils.RestUtils;
import com.huawei.dmestore.utils.VCSDKUtils;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * DmeAccessServiceImpl Tester.
 *
 * @author wangxiangyong
 * @version 1.0
 * @since <pre>十一月 11, 2020</pre>
 */
public class DmeAccessServiceImplTest {
    private Gson gson = new Gson();

    private String hostIp = "10.143.133.12";

    private int hostPort = 26335;

    private String userName = "evuser";

    private String password = "Pbu4@1234";

    private String baseUrl = "https://" + hostIp + ":" + hostPort;

    private RestTemplate restTemplate;

    @Mock
    private RestUtils restUtils;

    @Mock
    private VCSDKUtils vcsdkUtils;

    @Mock
    private DmeInfoDao dmeInfoDao;

    @Mock
    private VmfsAccessService vmfsAccessService;

    @Mock
    private DmeNFSAccessService dmeNfsAccessService;

    @Mock
    private ScheduleDao scheduleDao;

    @Mock
    private ScheduleSetting scheduleSetting;

    @Mock
    private TaskService taskService;

    @InjectMocks
    private DmeAccessService dmeAccessService = new DmeAccessServiceImpl();

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        restTemplate = mock(RestTemplate.class);
        when(restUtils.getRestTemplate()).thenReturn(restTemplate);
        login();
    }

    private HttpHeaders getHeaders(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        if (!StringUtils.isEmpty(token)) {
            headers.set("X-Auth-Token", token);
        }
        return headers;
    }

    private void login() throws Exception {
        Map<String, Object> params = new HashMap<>();
        params.put("userName", userName);
        params.put("password", password);
        params.put("hostIp", hostIp);
        params.put("hostPort", hostPort);
        HttpHeaders headers = getHeaders(null);
        Map<String, Object> requestbody = new HashMap<>(16);
        requestbody.put("grantType", "password");
        requestbody.put("userName", userName);
        requestbody.put("value", password);
        String hostUrl = "https://" + hostIp + ":" + hostPort + "/rest/plat/smapp/v1/sessions";
        HttpEntity<String> entity = new HttpEntity<>(gson.toJson(requestbody), headers);
        String jsonData
            = "{\"accessSession\":\"111\",\"roaRand\":\"66a102ebfe8fa8bce046a5f4778f79b5e00faf58b7422e70\",\"expires\":1800,\"additionalInfo\":null}";
        ResponseEntity<String> responseEntity = new ResponseEntity<>(jsonData, null, HttpStatus.OK);
        when(restTemplate.exchange(hostUrl, HttpMethod.PUT, entity, String.class)).thenReturn(responseEntity);
        when(dmeInfoDao.addDmeInfo(anyObject())).thenReturn(1);
        DmeInfo dmeInfo = new DmeInfo();
        dmeInfo.setUserName(userName);
        dmeInfo.setPassword(password);
        dmeInfo.setHostIp(hostIp);
        dmeInfo.setHostPort(hostPort);
        dmeInfo.setId(11111);
        dmeInfo.setState(1);
        dmeInfo.setCreateTime(new Date());
        dmeInfo.setUpdateTime(new Date());
        when(dmeInfoDao.getDmeInfo()).thenReturn(dmeInfo);
    }

    /**
     * Method: accessDme(Map<String, Object> params)
     */
    @Test
    public void testAccessDme() throws Exception {
        Map<String, Object> params = new HashMap<>();
        params.put("userName", userName);
        params.put("password", password);
        params.put("hostIp", hostIp);
        params.put("hostPort", hostPort);
        dmeAccessService.accessDme(params);
    }

    /**
     * Method: refreshDme()
     */
    @Test
    public void testRefreshDme() throws Exception {
        String url = baseUrl + "/rest/blockservice/v1/volumes?limit=1";
        HttpHeaders headers = getHeaders("111");
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        String jsonData
            = "{\"accessSession\":\"111\",\"roaRand\":\"66a102ebfe8fa8bce046a5f4778f79b5e00faf58b7422e70\",\"expires\":1800,\"additionalInfo\":null}";
        ResponseEntity<String> responseEntity = new ResponseEntity<>(jsonData, null, HttpStatus.OK);
        when(restTemplate.exchange(url, HttpMethod.GET, entity, String.class)).thenReturn(responseEntity);

        dmeAccessService.refreshDme();
    }

    /**
     * Method: access(String url, HttpMethod method, String requestBody)
     */
    @Test
    public void testAccess() throws Exception {
        String url = baseUrl + "/rest/blockservice/v1/volumes?limit=1";
        HttpHeaders headers = getHeaders("111");
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        String jsonData
            = "{\"accessSession\":\"111\",\"roaRand\":\"66a102ebfe8fa8bce046a5f4778f79b5e00faf58b7422e70\",\"expires\":1800,\"additionalInfo\":null}";
        ResponseEntity<String> responseEntity = new ResponseEntity<>(jsonData, null, HttpStatus.OK);
        when(restTemplate.exchange(url, HttpMethod.GET, entity, String.class)).thenReturn(responseEntity);
        dmeAccessService.access(url, HttpMethod.GET, null);
    }

    @Test
    public void testAccess401() throws Exception {
        String url = baseUrl + "/rest/blockservice/v1/volumes?limit=1";
        HttpHeaders headers = getHeaders("111");
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        String jsonData
            = "{\"accessSession\":\"111\",\"roaRand\":\"66a102ebfe8fa8bce046a5f4778f79b5e00faf58b7422e70\",\"expires\":1800,\"additionalInfo\":null}";
        ResponseEntity<String> responseEntity = new ResponseEntity<>(jsonData, null, HttpStatus.UNAUTHORIZED);
        when(restTemplate.exchange(url, HttpMethod.GET, entity, String.class)).thenReturn(responseEntity);
        dmeAccessService.access(url, HttpMethod.GET, null);
    }

    /**
     * Method: accessByJson(String url, HttpMethod method, String jsonBody)
     */
    @Test
    public void testAccessByJson() throws Exception {
        String url = baseUrl + "/rest/blockservice/v1/volumes?limit=1";
        HttpHeaders headers = getHeaders("111");
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        String jsonData
            = "{\"accessSession\":\"111\",\"roaRand\":\"66a102ebfe8fa8bce046a5f4778f79b5e00faf58b7422e70\",\"expires\":1800,\"additionalInfo\":null}";
        ResponseEntity<String> responseEntity = new ResponseEntity<>(jsonData, null, HttpStatus.OK);
        String jsonBody = null;
        when(restTemplate.exchange(url, HttpMethod.GET, entity, String.class, jsonBody)).thenReturn(responseEntity);
        dmeAccessService.accessByJson(url, HttpMethod.GET, null);
    }

    @Test
    public void testAccessByJson401() throws Exception {
        String url = baseUrl + "/rest/blockservice/v1/volumes?limit=1";
        HttpHeaders headers = getHeaders("111");
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        String jsonData
            = "{\"accessSession\":\"111\",\"roaRand\":\"66a102ebfe8fa8bce046a5f4778f79b5e00faf58b7422e70\",\"expires\":1800,\"additionalInfo\":null}";
        ResponseEntity<String> responseEntity = new ResponseEntity<>(jsonData, null, HttpStatus.UNAUTHORIZED);
        String jsonBody = null;
        when(restTemplate.exchange(url, HttpMethod.GET, entity, String.class, jsonBody)).thenReturn(responseEntity);
        dmeAccessService.accessByJson(url, HttpMethod.GET, null);
    }

    /**
     * Method: getWorkLoads(String storageId)
     */
    @Test
    public void testGetWorkLoads() throws Exception {
        String storageId = "b94bff9d-0dfb-11eb-bd3d-0050568491c9";
        String workloadsUrl = baseUrl + "/rest/storagemgmt/v1/storages/{storage_id}/workloads".replace("{storage_id}",
            storageId);
        HttpHeaders headers = getHeaders("111");
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        String jsonData
            = "{\"datas\":[{\"block_size\":\"1\",\"create_type\":\"2\",\"enable_compress\":false,\"enable_dedup\":true,\"id\":\"121312\",\"name\":\"53s\",\"type\":\"112\"}]}";
        ResponseEntity<String> responseEntity = new ResponseEntity<>(jsonData, null, HttpStatus.OK);
        when(restTemplate.exchange(workloadsUrl, HttpMethod.GET, entity, String.class)).thenReturn(responseEntity);
        dmeAccessService.getWorkLoads(storageId);
    }

    /**
     * Method: getDmeHosts(String hostIp)
     */
    @Test
    public void testGetDmeHosts() throws Exception {
        String hostIp = "10.143.133.17";
        Map<String, Object> requestbody = new HashMap<>(16);
        requestbody.put("ip", hostIp);
        String getHostsUrl = baseUrl + "/rest/hostmgmt/v1/hosts/summary";
        HttpHeaders headers = getHeaders("111");
        HttpEntity<String> entity = new HttpEntity<>(gson.toJson(requestbody), headers);
        String jsonData
            = "{\"total\":1,\"hosts\":[{\"id\":\"9cbd24b5-fb5b-4ad9-9393-cf05b9b97339\",\"project_id\":null,\"name\":\"10.143.133.17\",\"ip\":\"10.143.133.17\",\"display_status\":\"NORMAL\",\"managed_status\":\"NORMAL\",\"takeover_failed_reason\":{\"error_code\":null,\"error_msg\":null,\"error_args\":[]},\"os_status\":\"ONLINE\",\"overall_status\":\"\",\"os_type\":\"LINUX\",\"initiator_count\":1,\"access_mode\":\"NONE\",\"hostGroups\":[{\"id\":\"1231\",\"name\":\"22\"}],\"azs\":[]}]}";
        ResponseEntity<String> responseEntity = new ResponseEntity<>(jsonData, null, HttpStatus.OK);
        when(restTemplate.exchange(getHostsUrl, HttpMethod.POST, entity, String.class)).thenReturn(responseEntity);
        dmeAccessService.getDmeHosts(hostIp);
    }

    /**
     * Method: getDmeHostInitiators(String hostId)
     */
    @Test
    public void testGetDmeHostInitiators() throws Exception {
        String hostId = "1111";
        Map<String, Object> requestbody = new HashMap<>(16);
        requestbody.put("ip", hostIp);
        String url = baseUrl + "/rest/hostmgmt/v1/hosts/{host_id}/initiators";
        url = url.replace("{host_id}", hostId);
        HttpHeaders headers = getHeaders("111");
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        String jsonData
            = "{\"initiators\": [{\"id\": \"9cbd24b5-fb5b-4ad9-9393-cf05b9b97339\",\"port_name\": \"13213\",\"status\": \"11\",\"protocol\": \"ipv4\"}]}";
        ResponseEntity<String> responseEntity = new ResponseEntity<>(jsonData, null, HttpStatus.OK);
        when(restTemplate.exchange(url, HttpMethod.GET, entity, String.class)).thenReturn(responseEntity);
        dmeAccessService.getDmeHostInitiators(hostId);
    }

    /**
     * Method: getDmeHostGroups(String hostGroupName)
     */
    @Test
    public void testGetDmeHostGroups() throws Exception {
        String hostGroupName = "domain-c1087";
        Map<String, Object> requestbody = new HashMap<>(16);
        requestbody.put("name", hostGroupName);
        String url = baseUrl + "/rest/hostmgmt/v1/hostgroups/summary";
        HttpHeaders headers = getHeaders("111");
        HttpEntity<String> entity = new HttpEntity<>(gson.toJson(requestbody), headers);
        String jsonData
            = "{\"hostgroups\": [{\"id\": \"9cbd24b5-fb5b-4ad9-9393-cf05b9b97339\",\"name\": \"13213\",\"ip\": 2,\"source_type\": \"ipv4\",\"managed_status\": \"1\",\"project_id\": \"131\"}]}";
        ResponseEntity<String> responseEntity = new ResponseEntity<>(jsonData, null, HttpStatus.OK);
        when(restTemplate.exchange(url, HttpMethod.POST, entity, String.class)).thenReturn(responseEntity);

        dmeAccessService.getDmeHostGroups(hostGroupName);
    }

    /**
     * Method: createHost(Map<String, Object> params)
     */
    @Test
    public void testCreateHost() throws Exception {
        String hostId = "1456";
        String host = "10.143.132.17";
        Map<String, Object> hbamap = new HashMap<>(16);
        hbamap.put("type", "131");
        hbamap.put("name", "tes");
        when(vcsdkUtils.getHbaByHostObjectId(hostId)).thenReturn(hbamap);

        Map requestbody = new HashMap<>(16);
        requestbody.put("access_mode", "NONE");
        requestbody.put("type", "VMWAREESX");
        requestbody.put("ip", host);
        requestbody.put("host_name", host);
        List<Map<String, Object>> initiators = new ArrayList<>();
        Map<String, Object> initiator = new HashMap<>(16);
        initiator.put("protocol", hbamap.get("type"));
        initiator.put("port_name", hbamap.get("name"));
        initiators.add(initiator);
        requestbody.put("initiator", initiators);
        Map<String, Object> params = new HashMap<>(16);
        params.put("host", host);
        params.put("hostId", hostId);
        String url = baseUrl + "/rest/hostmgmt/v1/hosts";
        HttpHeaders headers = getHeaders("111");
        HttpEntity<String> entity = new HttpEntity<>(gson.toJson(requestbody), headers);
        String jsonData
            = "{\"id\":\"1231\",\"ip\":\"10.143.132.17\",\"access_mode\":\"1\",\"type\":\"111\",\"port\":26335}";
        ResponseEntity<String> responseEntity = new ResponseEntity<>(jsonData, null, HttpStatus.OK);
        when(restTemplate.exchange(url, HttpMethod.POST, entity, String.class)).thenReturn(responseEntity);

        dmeAccessService.createHost(params);
    }

    /**
     * Method: createHostGroup(Map<String, Object> params)
     */
    @Test
    public void testCreateHostGroup() throws Exception {
        String hostId = "1456";
        String host = "10.143.132.17";
        Map requestbody = new HashMap<>(16);
        requestbody.put("name", host);
        requestbody.put("host_ids", hostId);
        Map<String, Object> params = new HashMap<>(16);
        params.put("cluster", host);
        params.put("hostids", hostId);
        String url = baseUrl + "/rest/hostmgmt/v1/hostgroups";
        HttpHeaders headers = getHeaders("111");
        HttpEntity<String> entity = new HttpEntity<>(gson.toJson(requestbody), headers);
        String jsonData = "{\"id\":\"1231\",\"name\":\"10.143.132.17\"}";
        ResponseEntity<String> responseEntity = new ResponseEntity<>(jsonData, null, HttpStatus.OK);
        when(restTemplate.exchange(url, HttpMethod.POST, entity, String.class)).thenReturn(responseEntity);

        dmeAccessService.createHostGroup(params);
    }

    /**
     * Method: getDmeHost(String hostId)
     */
    @Test
    public void testGetDmeHost() throws Exception {
        String hostId = "1456";
        String host = "10.143.132.17";
        Map requestbody = new HashMap<>(16);
        requestbody.put("name", host);
        requestbody.put("host_ids", hostId);
        Map<String, Object> params = new HashMap<>(16);
        params.put("cluster", host);
        params.put("hostids", hostId);
        String url = baseUrl + "/rest/hostmgmt/v1/hosts/{host_id}/summary";
        url = url.replace("{host_id}", hostId);
        HttpHeaders headers = getHeaders("111");
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        String jsonData = "{\"id\":\"1231\",\"name\":\"10.143.132.17\"}";
        ResponseEntity<String> responseEntity = new ResponseEntity<>(jsonData, null, HttpStatus.OK);
        when(restTemplate.exchange(url, HttpMethod.GET, entity, String.class)).thenReturn(responseEntity);

        dmeAccessService.getDmeHost(hostId);
    }

    /**
     * Method: scanDatastore(String storageType)
     */
    @Test
    public void testScanDatastore() throws Exception {
        when(vmfsAccessService.scanVmfs()).thenReturn(true);
        when(dmeNfsAccessService.scanNfs()).thenReturn(true);
        String[] types = {"VMFS", "NFS", "ALL"};
        for (String type : types) {
            dmeAccessService.scanDatastore(type);
        }
    }

    /**
     * Method: configureTaskTime(Integer taskId, String taskCron)
     */
    @Test
    public void testConfigureTaskTime() throws Exception {
        Integer taskId = 123;
        String taskCron = "11";
        when(scheduleDao.updateTaskTime(taskId, taskCron)).thenReturn(1);
        doNothing().when(scheduleSetting).refreshTasks(taskId, taskCron);

        dmeAccessService.configureTaskTime(taskId, taskCron);
    }

    /**
     * Method: getDmeHostGroup(String hostGroupId)
     */
    @Test
    public void testGetDmeHostGroup() throws Exception {
        String hostGroupId = "123cfad3-2ddf-4b47-9772-675c6ad770b8";
        String url = baseUrl + "/rest/hostmgmt/v1/hostgroups/{hostgroup_id}/summary";
        url = url.replace("{hostgroup_id}", hostGroupId);
        HttpHeaders headers = getHeaders("111");
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        String jsonData = "{\"id\":\"1231\",\"name\":\"10.143.132.17\"}";
        ResponseEntity<String> responseEntity = new ResponseEntity<>(jsonData, null, HttpStatus.OK);
        when(restTemplate.exchange(url, HttpMethod.GET, entity, String.class)).thenReturn(responseEntity);

        dmeAccessService.getDmeHostGroup(hostGroupId);
    }

    /**
     * Method: getDmeHostInHostGroup(String hostGroupId)
     */
    @Test
    public void testGetDmeHostInHostGroup() throws Exception {
        String hostGroupId = "123cfad3-2ddf-4b47-9772-675c6ad770b8";

        String url = baseUrl + "/rest/hostmgmt/v1/hostgroups/{hostgroup_id}/hosts/list";
        url = url.replace("{hostgroup_id}", hostGroupId);
        Map<String, Object> requestbody = new HashMap<>(16);
        HttpHeaders headers = getHeaders("111");
        HttpEntity<String> entity = new HttpEntity<>(gson.toJson(requestbody), headers);
        String jsonData = "{\"hosts\": [{\"id\": \"1231\",\"name\": \"10.143.132.17\",\"ip\": \"1\"}]}";
        ResponseEntity<String> responseEntity = new ResponseEntity<>(jsonData, null, HttpStatus.OK);
        when(restTemplate.exchange(url, HttpMethod.POST, entity, String.class)).thenReturn(responseEntity);

        dmeAccessService.getDmeHostInHostGroup(hostGroupId);
    }

    /**
     * Method: deleteVolumes(List<String> ids)
     */
    @Test
    public void testDeleteVolumes() throws Exception {
        List<String> ids = new ArrayList<>();
        ids.add("59582fc9-d05c-4e28-8c50-da32d2a49e1b");

        String url = baseUrl + "/rest/blockservice/v1/volumes/delete";
        JsonObject body = new JsonObject();
        JsonArray array = new JsonParser().parse(gson.toJson(ids)).getAsJsonArray();
        body.add("volume_ids", array);
        HttpHeaders headers = getHeaders("111");
        HttpEntity<String> entity = new HttpEntity<>(gson.toJson(body), headers);
        String jsonData = "{\"task_id\":\"1123\"}";
        ResponseEntity<String> responseEntity = new ResponseEntity<>(jsonData, null, HttpStatus.OK);
        when(restTemplate.exchange(url, HttpMethod.POST, entity, String.class)).thenReturn(responseEntity);
        JsonObject taskDetail = new JsonObject();
        taskDetail.addProperty("status", 3);
        when(taskService.queryTaskByIdUntilFinish("1123")).thenReturn(taskDetail);
        dmeAccessService.deleteVolumes(ids);
    }

    /**
     * Method: unMapHost(String hostId, List<String> ids)
     */
    @Test
    public void testUnMapHost() throws Exception {
        String hostId = "9cbd24b5-fb5b-4ad9-9393-cf05b9b97339";
        List<String> ids = new ArrayList<>();
        ids.add("59582fc9-d05c-4e28-8c50-da32d2a49e1b");

        String url = baseUrl + "/rest/blockservice/v1/volumes/host-unmapping";
        JsonObject body = new JsonObject();
        JsonArray array = new JsonParser().parse(gson.toJson(ids)).getAsJsonArray();
        body.add("volume_ids", array);
        body.addProperty("host_id", hostId);
        HttpHeaders headers = getHeaders("111");
        HttpEntity<String> entity = new HttpEntity<>(body.toString(), headers);
        String jsonData = "{\"task_id\":\"1123\"}";
        ResponseEntity<String> responseEntity = new ResponseEntity<>(jsonData, null, HttpStatus.OK);
        when(restTemplate.exchange(url, HttpMethod.POST, entity, String.class)).thenReturn(responseEntity);
        JsonObject taskDetail = new JsonObject();
        taskDetail.addProperty("status", 3);
        when(taskService.queryTaskByIdUntilFinish("1123")).thenReturn(taskDetail);

        dmeAccessService.unMapHost(hostId, ids);
    }

    /**
     * Method: hostMapping(String hostId, List<String> volumeIds)
     */
    @Test
    public void testHostMapping() throws Exception {
        String hostId = "9cbd24b5-fb5b-4ad9-9393-cf05b9b97339";
        List<String> volumeIds = new ArrayList<>();
        volumeIds.add("589e368c-6f08-45c8-a75c-b4dc28a6dcca");
        String url = baseUrl + "/rest/blockservice/v1/volumes/host-mapping";
        JsonObject body = new JsonObject();
        body.addProperty("host_id", hostId);
        JsonArray volumeIdArray = gson.fromJson(gson.toJson(volumeIds), JsonArray.class);
        body.add("volume_ids", volumeIdArray);
        HttpHeaders headers = getHeaders("111");
        HttpEntity<String> entity = new HttpEntity<>(body.toString(), headers);
        String jsonData = "{\"task_id\":\"1123\"}";
        ResponseEntity<String> responseEntity = new ResponseEntity<>(jsonData, null, HttpStatus.OK);
        when(restTemplate.exchange(url, HttpMethod.POST, entity, String.class)).thenReturn(responseEntity);
        JsonObject taskDetail = new JsonObject();
        taskDetail.addProperty("status", 3);
        when(taskService.queryTaskByIdUntilFinish("1123")).thenReturn(taskDetail);

        dmeAccessService.hostMapping(hostId, volumeIds);
    }

}
