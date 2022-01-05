@Test public void testInstallAndStartService() throws Exception {
  testCreateServiceComponentHostSimple();
  String clusterName="foo1";
  String serviceName="HDFS";
  Cluster cluster=clusters.getCluster(clusterName);
  Service s1=cluster.getService(serviceName);
  Map<String,Config> configs=new HashMap<String,Config>();
  Map<String,String> properties=new HashMap<String,String>();
  properties.put("a","a1");
  properties.put("b","b1");
  Config c1=new ConfigImpl(cluster,"hdfs-site",properties,injector);
  properties.put("c","c1");
  properties.put("d","d1");
  Config c2=new ConfigImpl(cluster,"core-site",properties,injector);
  Config c3=new ConfigImpl(cluster,"foo-site",properties,injector);
  c1.setVersionTag("v1");
  c2.setVersionTag("v1");
  c3.setVersionTag("v1");
  cluster.addDesiredConfig(c1);
  cluster.addDesiredConfig(c2);
  cluster.addDesiredConfig(c3);
  c1.persist();
  c2.persist();
  c3.persist();
  configs.put(c1.getType(),c1);
  configs.put(c2.getType(),c2);
  s1.updateDesiredConfigs(configs);
  s1.persist();
  ServiceRequest r=new ServiceRequest(clusterName,serviceName,null,State.INSTALLED.toString());
  Set<ServiceRequest> requests=new HashSet<ServiceRequest>();
  requests.add(r);
  RequestStatusResponse trackAction=controller.updateServices(requests);
  Assert.assertEquals(State.INSTALLED,clusters.getCluster(clusterName).getService(serviceName).getDesiredState());
  for (  ServiceComponent sc : clusters.getCluster(clusterName).getService(serviceName).getServiceComponents().values()) {
    Assert.assertEquals(State.INSTALLED,sc.getDesiredState());
    for (    ServiceComponentHost sch : sc.getServiceComponentHosts().values()) {
      Assert.assertEquals(State.INSTALLED,sch.getDesiredState());
      Assert.assertEquals(State.INIT,sch.getState());
    }
  }
  List<ShortTaskStatus> taskStatuses=trackAction.getTasks();
  Assert.assertEquals(5,taskStatuses.size());
  boolean foundH1NN=false;
  boolean foundH1DN=false;
  boolean foundH2DN=false;
  boolean foundH1CLT=false;
  boolean foundH2CLT=false;
  for (  ShortTaskStatus taskStatus : taskStatuses) {
    LOG.debug("Task dump :" + taskStatus.toString());
    Assert.assertEquals(RoleCommand.INSTALL.toString(),taskStatus.getCommand());
    Assert.assertEquals(HostRoleStatus.PENDING.toString(),taskStatus.getStatus());
    if (taskStatus.getHostName().equals("h1")) {
      if (Role.NAMENODE.toString().equals(taskStatus.getRole())) {
        foundH1NN=true;
      }
 else       if (Role.DATANODE.toString().equals(taskStatus.getRole())) {
        foundH1DN=true;
      }
 else       if (Role.HDFS_CLIENT.toString().equals(taskStatus.getRole())) {
        foundH1CLT=true;
      }
 else {
        fail("Found invalid role for host h1");
      }
    }
 else     if (taskStatus.getHostName().equals("h2")) {
      if (Role.DATANODE.toString().equals(taskStatus.getRole())) {
        foundH2DN=true;
      }
 else       if (Role.HDFS_CLIENT.toString().equals(taskStatus.getRole())) {
        foundH2CLT=true;
      }
 else {
        fail("Found invalid role for host h2");
      }
    }
 else {
      fail("Found invalid host in task list");
    }
  }
  Assert.assertTrue(foundH1DN && foundH1NN && foundH2DN&& foundH1CLT&& foundH2CLT);
  List<Stage> stages=actionDB.getAllStages(trackAction.getRequestId());
  Assert.assertEquals(1,stages.size());
  for (  Stage stage : stages) {
    LOG.info("Stage Details for Install Service" + ", stageId=" + stage.getStageId() + ", actionId="+ stage.getActionId());
    for (    String host : stage.getHosts()) {
      for (      ExecutionCommandWrapper ecw : stage.getExecutionCommands(host)) {
        Assert.assertFalse(ecw.getExecutionCommand().getHostLevelParams().get("repo_info").isEmpty());
        LOG.info("Dumping host action details" + ", stageId=" + stage.getStageId() + ", actionId="+ stage.getActionId()+ ", commandDetails="+ StageUtils.jaxbToString(ecw.getExecutionCommand()));
      }
    }
  }
  RequestStatusRequest statusRequest=new RequestStatusRequest(trackAction.getRequestId(),null);
  Set<RequestStatusResponse> statusResponses=controller.getRequestStatus(statusRequest);
  Assert.assertEquals(1,statusResponses.size());
  RequestStatusResponse statusResponse=statusResponses.iterator().next();
  Assert.assertNotNull(statusResponse);
  Assert.assertEquals(trackAction.getRequestId(),statusResponse.getRequestId());
  Assert.assertEquals(5,statusResponse.getTasks().size());
  Set<TaskStatusRequest> taskRequests=new HashSet<TaskStatusRequest>();
  TaskStatusRequest t1, t2;
  t1=new TaskStatusRequest();
  t2=new TaskStatusRequest();
  t1.setRequestId(trackAction.getRequestId());
  taskRequests.add(t1);
  Set<TaskStatusResponse> taskResponses=controller.getTaskStatus(taskRequests);
  Assert.assertEquals(5,taskResponses.size());
  t1.setTaskId(1L);
  t2.setRequestId(trackAction.getRequestId());
  t2.setTaskId(2L);
  taskRequests.clear();
  taskRequests.add(t1);
  taskRequests.add(t2);
  taskResponses=controller.getTaskStatus(taskRequests);
  Assert.assertEquals(2,taskResponses.size());
  for (  ServiceComponent sc : clusters.getCluster(clusterName).getService(serviceName).getServiceComponents().values()) {
    for (    ServiceComponentHost sch : sc.getServiceComponentHosts().values()) {
      sch.setState(State.INSTALLED);
    }
  }
  r=new ServiceRequest(clusterName,serviceName,null,State.STARTED.toString());
  requests.clear();
  requests.add(r);
  trackAction=controller.updateServices(requests);
  Assert.assertEquals(State.STARTED,clusters.getCluster(clusterName).getService(serviceName).getDesiredState());
  for (  ServiceComponent sc : clusters.getCluster(clusterName).getService(serviceName).getServiceComponents().values()) {
    if (sc.getName().equals("HDFS_CLIENT")) {
      Assert.assertEquals(State.INSTALLED,sc.getDesiredState());
    }
 else {
      Assert.assertEquals(State.STARTED,sc.getDesiredState());
    }
    for (    ServiceComponentHost sch : sc.getServiceComponentHosts().values()) {
      if (sch.getServiceComponentName().equals("HDFS_CLIENT")) {
        Assert.assertEquals(State.INSTALLED,sch.getDesiredState());
      }
 else {
        Assert.assertEquals(State.STARTED,sch.getDesiredState());
      }
    }
  }
  stages=actionDB.getAllStages(trackAction.getRequestId());
  Assert.assertEquals(2,stages.size());
  for (  Stage stage : stages) {
    LOG.info("Stage Details for Start Service" + ", stageId=" + stage.getStageId() + ", actionId="+ stage.getActionId());
    for (    String host : stage.getHosts()) {
      LOG.info("Dumping host action details" + ", stageId=" + stage.getStageId() + ", actionId="+ stage.getActionId()+ ", commandDetails="+ StageUtils.jaxbToString(stage.getExecutionCommands(host).get(0)));
    }
  }
  StringBuilder sb=new StringBuilder();
  clusters.debugDump(sb);
  LOG.info("Cluster Dump: " + sb.toString());
  statusRequest=new RequestStatusRequest(null,null);
  statusResponses=controller.getRequestStatus(statusRequest);
  Assert.assertEquals(2,statusResponses.size());
  int counter=0;
  for (  ServiceComponent sc : clusters.getCluster(clusterName).getService(serviceName).getServiceComponents().values()) {
    for (    ServiceComponentHost sch : sc.getServiceComponentHosts().values()) {
      if (sc.isClientComponent()) {
        sch.setState(State.INSTALLED);
      }
 else {
        ++counter;
switch (counter % 1) {
case 0:
          sch.setState(State.START_FAILED);
        break;
case 1:
      sch.setState(State.STOP_FAILED);
    break;
case 2:
  sch.setState(State.STARTED);
break;
}
}
}
}
r=new ServiceRequest(clusterName,serviceName,null,State.INSTALLED.toString());
requests.clear();
requests.add(r);
trackAction=controller.updateServices(requests);
Assert.assertEquals(State.INSTALLED,clusters.getCluster(clusterName).getService(serviceName).getDesiredState());
for (ServiceComponent sc : clusters.getCluster(clusterName).getService(serviceName).getServiceComponents().values()) {
Assert.assertEquals(State.INSTALLED,sc.getDesiredState());
for (ServiceComponentHost sch : sc.getServiceComponentHosts().values()) {
Assert.assertEquals(State.INSTALLED,sch.getDesiredState());
}
}
stages=actionDB.getAllStages(trackAction.getRequestId());
for (Stage stage : stages) {
LOG.info("Stage Details for Stop Service : " + stage.toString());
}
Assert.assertEquals(1,stages.size());
}
