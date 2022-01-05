@Test public void testServiceComponentHostUpdateRecursive() throws AmbariException {
  String clusterName="foo1";
  createCluster(clusterName);
  String serviceName1="HDFS";
  createService(clusterName,serviceName1,null);
  String componentName1="NAMENODE";
  String componentName2="DATANODE";
  String componentName3="HDFS_CLIENT";
  createServiceComponent(clusterName,serviceName1,componentName1,State.INIT);
  createServiceComponent(clusterName,serviceName1,componentName2,State.INIT);
  createServiceComponent(clusterName,serviceName1,componentName3,State.INIT);
  String host1="h1";
  clusters.addHost(host1);
  clusters.getHost("h1").setOsType("centos5");
  clusters.getHost("h1").persist();
  String host2="h2";
  clusters.addHost(host2);
  clusters.getHost("h2").setOsType("centos5");
  clusters.getHost("h2").persist();
  clusters.mapHostToCluster(host1,clusterName);
  clusters.mapHostToCluster(host2,clusterName);
  Set<ServiceComponentHostRequest> set1=new HashSet<ServiceComponentHostRequest>();
  ServiceComponentHostRequest r1=new ServiceComponentHostRequest(clusterName,serviceName1,componentName1,host1,null,State.INIT.toString());
  ServiceComponentHostRequest r2=new ServiceComponentHostRequest(clusterName,serviceName1,componentName2,host1,null,State.INIT.toString());
  ServiceComponentHostRequest r3=new ServiceComponentHostRequest(clusterName,serviceName1,componentName1,host2,null,State.INIT.toString());
  ServiceComponentHostRequest r4=new ServiceComponentHostRequest(clusterName,serviceName1,componentName2,host2,null,State.INIT.toString());
  ServiceComponentHostRequest r5=new ServiceComponentHostRequest(clusterName,serviceName1,componentName3,host1,null,State.INIT.toString());
  set1.add(r1);
  set1.add(r2);
  set1.add(r3);
  set1.add(r4);
  set1.add(r5);
  controller.createHostComponents(set1);
  Cluster c1=clusters.getCluster(clusterName);
  Service s1=c1.getService(serviceName1);
  ServiceComponent sc1=s1.getServiceComponent(componentName1);
  ServiceComponent sc2=s1.getServiceComponent(componentName2);
  ServiceComponent sc3=s1.getServiceComponent(componentName3);
  ServiceComponentHost sch1=sc1.getServiceComponentHost(host1);
  ServiceComponentHost sch2=sc2.getServiceComponentHost(host1);
  ServiceComponentHost sch3=sc1.getServiceComponentHost(host2);
  ServiceComponentHost sch4=sc2.getServiceComponentHost(host2);
  ServiceComponentHost sch5=sc3.getServiceComponentHost(host1);
  s1.setDesiredState(State.INIT);
  sc1.setDesiredState(State.INIT);
  sc2.setDesiredState(State.INIT);
  sc3.setDesiredState(State.INIT);
  sch1.setDesiredState(State.INIT);
  sch2.setDesiredState(State.INIT);
  sch3.setDesiredState(State.INIT);
  sch4.setDesiredState(State.INSTALLED);
  sch5.setDesiredState(State.INSTALLED);
  sch1.setState(State.INIT);
  sch2.setState(State.INSTALL_FAILED);
  sch3.setState(State.INIT);
  sch4.setState(State.INSTALLED);
  sch5.setState(State.INSTALLED);
  ServiceComponentHostRequest req1, req2, req3, req4, req5;
  Set<ServiceComponentHostRequest> reqs=new HashSet<ServiceComponentHostRequest>();
  try {
    reqs.clear();
    req1=new ServiceComponentHostRequest(clusterName,serviceName1,componentName1,host1,null,State.STARTED.toString());
    reqs.add(req1);
    controller.updateHostComponents(reqs);
    fail("Expected failure for invalid transition");
  }
 catch (  Exception e) {
  }
  try {
    reqs.clear();
    req1=new ServiceComponentHostRequest(clusterName,serviceName1,componentName1,host1,null,State.INSTALLED.toString());
    req2=new ServiceComponentHostRequest(clusterName,serviceName1,componentName1,host2,null,State.INSTALLED.toString());
    req3=new ServiceComponentHostRequest(clusterName,serviceName1,componentName2,host1,null,State.INSTALLED.toString());
    req4=new ServiceComponentHostRequest(clusterName,serviceName1,componentName2,host2,null,State.INSTALLED.toString());
    req5=new ServiceComponentHostRequest(clusterName,serviceName1,componentName3,host1,null,State.STARTED.toString());
    reqs.add(req1);
    reqs.add(req2);
    reqs.add(req3);
    reqs.add(req4);
    reqs.add(req5);
    controller.updateHostComponents(reqs);
    fail("Expected failure for invalid states");
  }
 catch (  Exception e) {
  }
  reqs.clear();
  req1=new ServiceComponentHostRequest(clusterName,null,componentName1,host1,null,State.INSTALLED.toString());
  req2=new ServiceComponentHostRequest(clusterName,serviceName1,componentName1,host2,null,State.INSTALLED.toString());
  req3=new ServiceComponentHostRequest(clusterName,null,componentName2,host1,null,State.INSTALLED.toString());
  req4=new ServiceComponentHostRequest(clusterName,serviceName1,componentName2,host2,null,State.INSTALLED.toString());
  req5=new ServiceComponentHostRequest(clusterName,serviceName1,componentName3,host1,null,State.INSTALLED.toString());
  reqs.add(req1);
  reqs.add(req2);
  reqs.add(req3);
  reqs.add(req4);
  reqs.add(req5);
  RequestStatusResponse trackAction=controller.updateHostComponents(reqs);
  Assert.assertNotNull(trackAction);
  long requestId=trackAction.getRequestId();
  Assert.assertFalse(actionDB.getAllStages(requestId).isEmpty());
  List<Stage> stages=actionDB.getAllStages(requestId);
  for (  Stage stage : stages) {
    LOG.debug("Stage dump: " + stage.toString());
  }
  sch1.setState(State.INSTALLED);
  sch2.setState(State.INSTALLED);
  sch3.setState(State.INSTALLED);
  sch4.setState(State.INSTALLED);
  sch5.setState(State.INSTALLED);
  reqs.clear();
  req1=new ServiceComponentHostRequest(clusterName,serviceName1,componentName1,host1,null,State.INSTALLED.toString());
  req2=new ServiceComponentHostRequest(clusterName,serviceName1,componentName1,host2,null,State.INSTALLED.toString());
  reqs.add(req1);
  reqs.add(req2);
  trackAction=controller.updateHostComponents(reqs);
  Assert.assertNull(trackAction);
}
