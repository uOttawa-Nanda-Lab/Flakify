@Test public void testClientServiceSmokeTests() throws AmbariException {
  String clusterName="foo1";
  createCluster(clusterName);
  clusters.getCluster(clusterName).setDesiredStackVersion(new StackId("HDP-0.1"));
  String serviceName="PIG";
  createService(clusterName,serviceName,null);
  String componentName1="PIG";
  createServiceComponent(clusterName,serviceName,componentName1,State.INIT);
  String host1="h1";
  clusters.addHost(host1);
  clusters.getHost("h1").persist();
  String host2="h2";
  clusters.addHost(host2);
  clusters.getHost("h2").persist();
  clusters.getHost("h1").setOsType("centos5");
  clusters.getHost("h2").setOsType("centos6");
  clusters.mapHostToCluster(host1,clusterName);
  clusters.mapHostToCluster(host2,clusterName);
  createServiceComponentHost(clusterName,null,componentName1,host1,null);
  createServiceComponentHost(clusterName,null,componentName1,host2,null);
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
  Assert.assertEquals(2,taskStatuses.size());
  List<Stage> stages=actionDB.getAllStages(trackAction.getRequestId());
  Assert.assertEquals(1,stages.size());
  for (  ServiceComponent sc : clusters.getCluster(clusterName).getService(serviceName).getServiceComponents().values()) {
    for (    ServiceComponentHost sch : sc.getServiceComponentHosts().values()) {
      sch.setState(State.INSTALLED);
    }
  }
  r=new ServiceRequest(clusterName,serviceName,null,State.STARTED.toString());
  requests.clear();
  requests.add(r);
  trackAction=controller.updateServices(requests);
  Assert.assertNotNull(trackAction);
  Assert.assertEquals(State.INSTALLED,clusters.getCluster(clusterName).getService(serviceName).getDesiredState());
  for (  ServiceComponent sc : clusters.getCluster(clusterName).getService(serviceName).getServiceComponents().values()) {
    Assert.assertEquals(State.INSTALLED,sc.getDesiredState());
    for (    ServiceComponentHost sch : sc.getServiceComponentHosts().values()) {
      Assert.assertEquals(State.INSTALLED,sch.getDesiredState());
      Assert.assertEquals(State.INSTALLED,sch.getState());
    }
  }
  stages=actionDB.getAllStages(trackAction.getRequestId());
  for (  Stage s : stages) {
    LOG.info("Stage dump : " + s.toString());
  }
  Assert.assertEquals(1,stages.size());
  taskStatuses=trackAction.getTasks();
  Assert.assertEquals(1,taskStatuses.size());
  Assert.assertEquals(Role.PIG_SERVICE_CHECK.toString(),taskStatuses.get(0).getRole());
}
