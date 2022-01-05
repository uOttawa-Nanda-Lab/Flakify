@Test public void testRcaOnJobtrackerHost() throws AmbariException {
  String clusterName="foo1";
  createCluster(clusterName);
  clusters.getCluster(clusterName).setDesiredStackVersion(new StackId("HDP-0.1"));
  String serviceName="MAPREDUCE";
  createService(clusterName,serviceName,null);
  String componentName1="JOBTRACKER";
  String componentName2="TASKTRACKER";
  String componentName3="MAPREDUCE_CLIENT";
  createServiceComponent(clusterName,serviceName,componentName1,State.INIT);
  createServiceComponent(clusterName,serviceName,componentName2,State.INIT);
  createServiceComponent(clusterName,serviceName,componentName3,State.INIT);
  String host1="h1";
  clusters.addHost(host1);
  clusters.getHost("h1").setOsType("centos5");
  clusters.getHost("h1").persist();
  String host2="h2";
  clusters.addHost(host2);
  clusters.getHost("h2").setOsType("centos6");
  clusters.getHost("h2").persist();
  clusters.mapHostToCluster(host1,clusterName);
  clusters.mapHostToCluster(host2,clusterName);
  createServiceComponentHost(clusterName,serviceName,componentName1,host1,null);
  createServiceComponentHost(clusterName,serviceName,componentName2,host1,null);
  createServiceComponentHost(clusterName,serviceName,componentName2,host2,null);
  createServiceComponentHost(clusterName,serviceName,componentName3,host1,null);
  createServiceComponentHost(clusterName,serviceName,componentName3,host2,null);
  Map<String,String> configs=new HashMap<String,String>();
  configs.put("a","b");
  configs.put("rca_enabled","true");
  ConfigurationRequest cr1=new ConfigurationRequest(clusterName,"global","v1",configs);
  controller.createConfiguration(cr1);
  Set<ServiceRequest> sReqs=new HashSet<ServiceRequest>();
  Map<String,String> configVersions=new HashMap<String,String>();
  configVersions.put("global","v1");
  sReqs.clear();
  sReqs.add(new ServiceRequest(clusterName,serviceName,configVersions,"INSTALLED"));
  RequestStatusResponse trackAction=controller.updateServices(sReqs);
  List<Stage> stages=actionDB.getAllStages(trackAction.getRequestId());
  for (  ExecutionCommandWrapper cmd : stages.get(0).getExecutionCommands(host1)) {
    assertEquals("true",cmd.getExecutionCommand().getConfigurations().get("global").get("rca_enabled"));
  }
  for (  ExecutionCommandWrapper cmd : stages.get(0).getExecutionCommands(host2)) {
    assertEquals("false",cmd.getExecutionCommand().getConfigurations().get("global").get("rca_enabled"));
  }
}
