@SuppressWarnings("serial") @Test public void testGetRequestAndTaskStatus() throws AmbariException {
  long requestId=3;
  long stageId=4;
  String clusterName="c1";
  final String hostName1="h1";
  final String hostName2="h2";
  clusters.addCluster(clusterName);
  clusters.getCluster(clusterName).setDesiredStackVersion(new StackId("HDP-0.1"));
  clusters.addHost(hostName1);
  clusters.getHost("h1").setOsType("centos5");
  clusters.getHost(hostName1).persist();
  clusters.addHost(hostName2);
  clusters.getHost("h2").setOsType("centos5");
  clusters.getHost(hostName2).persist();
  clusters.mapHostsToCluster(new HashSet<String>(){
{
      add(hostName1);
      add(hostName2);
    }
  }
,clusterName);
  List<Stage> stages=new ArrayList<Stage>();
  stages.add(new Stage(requestId,"/a1",clusterName));
  stages.get(0).setStageId(stageId++);
  stages.get(0).addHostRoleExecutionCommand(hostName1,Role.HBASE_MASTER,RoleCommand.START,new ServiceComponentHostStartEvent(Role.HBASE_MASTER.toString(),hostName1,System.currentTimeMillis(),new HashMap<String,String>()),clusterName,"HBASE");
  stages.add(new Stage(requestId,"/a2",clusterName));
  stages.get(1).setStageId(stageId);
  stages.get(1).addHostRoleExecutionCommand(hostName1,Role.HBASE_CLIENT,RoleCommand.START,new ServiceComponentHostStartEvent(Role.HBASE_CLIENT.toString(),hostName1,System.currentTimeMillis(),new HashMap<String,String>()),clusterName,"HBASE");
  actionDB.persistActions(stages);
  Set<RequestStatusResponse> requestStatusResponses=controller.getRequestStatus(new RequestStatusRequest(requestId,null));
  RequestStatusResponse requestStatusResponse=requestStatusResponses.iterator().next();
  assertEquals(requestId,requestStatusResponse.getRequestId());
  assertEquals(2,requestStatusResponse.getTasks().size());
  ShortTaskStatus task1=requestStatusResponse.getTasks().get(0);
  ShortTaskStatus task2=requestStatusResponse.getTasks().get(1);
  assertEquals(RoleCommand.START.toString(),task1.getCommand());
  assertEquals(Role.HBASE_MASTER.toString(),task1.getRole());
  Set<TaskStatusRequest> taskStatusRequests=new HashSet<TaskStatusRequest>();
  taskStatusRequests.add(new TaskStatusRequest(requestId,task1.getTaskId()));
  taskStatusRequests.add(new TaskStatusRequest(requestId,task2.getTaskId()));
  Set<TaskStatusResponse> taskStatusResponses=controller.getTaskStatus(taskStatusRequests);
  assertEquals(2,taskStatusResponses.size());
}
