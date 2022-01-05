@SuppressWarnings("serial") @Test public void testCreateActions() throws Exception {
  clusters.addCluster("c1");
  clusters.getCluster("c1").setDesiredStackVersion(new StackId("HDP-0.1"));
  clusters.addHost("h1");
  clusters.getHost("h1").setOsType("centos5");
  clusters.getHost("h1").persist();
  clusters.addHost("h2");
  clusters.getHost("h2").setOsType("centos5");
  clusters.getHost("h2").persist();
  Set<String> hostNames=new HashSet<String>(){
{
      add("h1");
      add("h2");
    }
  }
;
  clusters.mapHostsToCluster(hostNames,"c1");
  Cluster cluster=clusters.getCluster("c1");
  cluster.setDesiredStackVersion(new StackId("HDP-0.1"));
  Service hdfs=cluster.addService("HDFS");
  Service mapReduce=cluster.addService("MAPREDUCE");
  hdfs.persist();
  mapReduce.persist();
  hdfs.addServiceComponent(Role.HDFS_CLIENT.name()).persist();
  mapReduce.addServiceComponent(Role.MAPREDUCE_CLIENT.name()).persist();
  hdfs.getServiceComponent(Role.HDFS_CLIENT.name()).addServiceComponentHost("h1").persist();
  mapReduce.getServiceComponent(Role.MAPREDUCE_CLIENT.name()).addServiceComponentHost("h2").persist();
  Set<ActionRequest> actionRequests=new HashSet<ActionRequest>();
  Map<String,String> params=new HashMap<String,String>(){
{
      put("test","test");
    }
  }
;
  ActionRequest actionRequest=new ActionRequest("c1","HDFS",Role.HDFS_SERVICE_CHECK.name(),params);
  actionRequests.add(actionRequest);
  RequestStatusResponse response=controller.createActions(actionRequests);
  assertEquals(1,response.getTasks().size());
  ShortTaskStatus task=response.getTasks().get(0);
  List<HostRoleCommand> storedTasks=actionDB.getRequestTasks(response.getRequestId());
  assertEquals(1,storedTasks.size());
  HostRoleCommand hostRoleCommand=storedTasks.get(0);
  assertEquals(task.getTaskId(),hostRoleCommand.getTaskId());
  assertEquals(actionRequest.getServiceName(),hostRoleCommand.getExecutionCommandWrapper().getExecutionCommand().getServiceName());
  assertEquals(actionRequest.getClusterName(),hostRoleCommand.getExecutionCommandWrapper().getExecutionCommand().getClusterName());
  assertEquals(actionRequest.getActionName(),hostRoleCommand.getExecutionCommandWrapper().getExecutionCommand().getRole().name());
  assertEquals(Role.HDFS_CLIENT.name(),hostRoleCommand.getEvent().getEvent().getServiceComponentName());
  assertEquals(actionRequest.getParameters(),hostRoleCommand.getExecutionCommandWrapper().getExecutionCommand().getRoleParams());
  actionRequests.add(new ActionRequest("c1","MAPREDUCE",Role.MAPREDUCE_SERVICE_CHECK.name(),null));
  response=controller.createActions(actionRequests);
  assertEquals(2,response.getTasks().size());
  List<HostRoleCommand> tasks=actionDB.getRequestTasks(response.getRequestId());
  assertEquals(2,tasks.size());
}
