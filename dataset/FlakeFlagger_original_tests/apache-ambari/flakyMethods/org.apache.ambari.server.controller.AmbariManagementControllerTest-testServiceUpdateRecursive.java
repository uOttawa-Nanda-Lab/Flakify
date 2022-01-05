@Test public void testServiceUpdateRecursive() throws AmbariException {
  String clusterName="foo1";
  createCluster(clusterName);
  clusters.getCluster(clusterName).setDesiredStackVersion(new StackId("HDP-0.2"));
  String serviceName1="HDFS";
  createService(clusterName,serviceName1,null);
  String serviceName2="HBASE";
  createService(clusterName,serviceName2,null);
  String componentName1="NAMENODE";
  String componentName2="DATANODE";
  String componentName3="HBASE_MASTER";
  String componentName4="HDFS_CLIENT";
  createServiceComponent(clusterName,serviceName1,componentName1,State.INIT);
  createServiceComponent(clusterName,serviceName1,componentName2,State.INIT);
  createServiceComponent(clusterName,serviceName2,componentName3,State.INIT);
  createServiceComponent(clusterName,serviceName1,componentName4,State.INIT);
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
  ServiceComponentHostRequest r5=new ServiceComponentHostRequest(clusterName,serviceName2,componentName3,host1,null,State.INIT.toString());
  ServiceComponentHostRequest r6=new ServiceComponentHostRequest(clusterName,serviceName1,componentName4,host2,null,State.INIT.toString());
  set1.add(r1);
  set1.add(r2);
  set1.add(r3);
  set1.add(r4);
  set1.add(r5);
  set1.add(r6);
  controller.createHostComponents(set1);
  Cluster c1=clusters.getCluster(clusterName);
  Service s1=c1.getService(serviceName1);
  Service s2=c1.getService(serviceName2);
  ServiceComponent sc1=s1.getServiceComponent(componentName1);
  ServiceComponent sc2=s1.getServiceComponent(componentName2);
  ServiceComponent sc3=s2.getServiceComponent(componentName3);
  ServiceComponent sc4=s1.getServiceComponent(componentName4);
  ServiceComponentHost sch1=sc1.getServiceComponentHost(host1);
  ServiceComponentHost sch2=sc2.getServiceComponentHost(host1);
  ServiceComponentHost sch3=sc1.getServiceComponentHost(host2);
  ServiceComponentHost sch4=sc2.getServiceComponentHost(host2);
  ServiceComponentHost sch5=sc3.getServiceComponentHost(host1);
  ServiceComponentHost sch6=sc4.getServiceComponentHost(host2);
  s1.setDesiredState(State.INSTALLED);
  s2.setDesiredState(State.INSTALLED);
  sc1.setDesiredState(State.STARTED);
  sc2.setDesiredState(State.INIT);
  sc3.setDesiredState(State.STARTED);
  sc4.setDesiredState(State.INSTALLED);
  sch1.setDesiredState(State.INSTALLED);
  sch2.setDesiredState(State.INSTALLED);
  sch3.setDesiredState(State.INSTALLED);
  sch4.setDesiredState(State.INSTALLED);
  sch5.setDesiredState(State.INSTALLED);
  sch6.setDesiredState(State.INSTALLED);
  sch1.setState(State.INSTALLED);
  sch2.setState(State.INSTALLED);
  sch3.setState(State.INSTALLED);
  sch4.setState(State.INSTALLED);
  sch5.setState(State.INSTALLED);
  sch6.setState(State.INSTALLED);
  Set<ServiceRequest> reqs=new HashSet<ServiceRequest>();
  ServiceRequest req1, req2;
  try {
    reqs.clear();
    req1=new ServiceRequest(clusterName,serviceName1,null,State.STARTED.toString());
    reqs.add(req1);
    controller.updateServices(reqs);
    fail("Expected failure for invalid state update");
  }
 catch (  Exception e) {
  }
  s1.setDesiredState(State.INSTALLED);
  s2.setDesiredState(State.INSTALLED);
  sc1.setDesiredState(State.STARTED);
  sc2.setDesiredState(State.INSTALLED);
  sc3.setDesiredState(State.STARTED);
  sch1.setDesiredState(State.INSTALLED);
  sch2.setDesiredState(State.INSTALLED);
  sch3.setDesiredState(State.INSTALLED);
  sch4.setDesiredState(State.INSTALLED);
  sch5.setDesiredState(State.INSTALLED);
  sch1.setState(State.INIT);
  sch2.setState(State.INSTALLED);
  sch3.setState(State.INIT);
  sch4.setState(State.INSTALLED);
  sch5.setState(State.INSTALLED);
  try {
    reqs.clear();
    req1=new ServiceRequest(clusterName,serviceName1,null,State.STARTED.toString());
    reqs.add(req1);
    controller.updateServices(reqs);
    fail("Expected failure for invalid state update");
  }
 catch (  Exception e) {
  }
  s1.setDesiredState(State.INSTALLED);
  s2.setDesiredState(State.INSTALLED);
  sc1.setDesiredState(State.STARTED);
  sc2.setDesiredState(State.INSTALLED);
  sc3.setDesiredState(State.STARTED);
  sch1.setDesiredState(State.STARTED);
  sch2.setDesiredState(State.STARTED);
  sch3.setDesiredState(State.STARTED);
  sch4.setDesiredState(State.STARTED);
  sch5.setDesiredState(State.STARTED);
  sch1.setState(State.INSTALLED);
  sch2.setState(State.START_FAILED);
  sch3.setState(State.INSTALLED);
  sch4.setState(State.STARTED);
  sch5.setState(State.INSTALLED);
  reqs.clear();
  req1=new ServiceRequest(clusterName,serviceName1,null,State.STARTED.toString());
  req2=new ServiceRequest(clusterName,serviceName2,null,State.STARTED.toString());
  reqs.add(req1);
  reqs.add(req2);
  RequestStatusResponse trackAction=controller.updateServices(reqs);
  Assert.assertEquals(State.STARTED,s1.getDesiredState());
  Assert.assertEquals(State.STARTED,s2.getDesiredState());
  Assert.assertEquals(State.STARTED,sc1.getDesiredState());
  Assert.assertEquals(State.STARTED,sc2.getDesiredState());
  Assert.assertEquals(State.STARTED,sc3.getDesiredState());
  Assert.assertEquals(State.INSTALLED,sc4.getDesiredState());
  Assert.assertEquals(State.STARTED,sch1.getDesiredState());
  Assert.assertEquals(State.STARTED,sch2.getDesiredState());
  Assert.assertEquals(State.STARTED,sch3.getDesiredState());
  Assert.assertEquals(State.STARTED,sch4.getDesiredState());
  Assert.assertEquals(State.STARTED,sch5.getDesiredState());
  Assert.assertEquals(State.INSTALLED,sch6.getDesiredState());
  Assert.assertEquals(State.INSTALLED,sch1.getState());
  Assert.assertEquals(State.START_FAILED,sch2.getState());
  Assert.assertEquals(State.INSTALLED,sch3.getState());
  Assert.assertEquals(State.STARTED,sch4.getState());
  Assert.assertEquals(State.INSTALLED,sch5.getState());
  Assert.assertEquals(State.INSTALLED,sch6.getState());
  long requestId=trackAction.getRequestId();
  List<Stage> stages=actionDB.getAllStages(requestId);
  for (  Stage stage : stages) {
    LOG.debug("Stage dump: " + stage.toString());
  }
  Assert.assertTrue(!stages.isEmpty());
  Assert.assertEquals(3,stages.size());
  Stage stage1=null, stage2=null, stage3=null;
  for (  Stage s : stages) {
    if (s.getStageId() == 1) {
      stage1=s;
    }
    if (s.getStageId() == 2) {
      stage2=s;
    }
    if (s.getStageId() == 3) {
      stage3=s;
    }
  }
  Assert.assertEquals(2,stage1.getExecutionCommands(host1).size());
  Assert.assertEquals(1,stage1.getExecutionCommands(host2).size());
  Assert.assertEquals(1,stage2.getExecutionCommands(host1).size());
  Assert.assertNotNull(stage1.getExecutionCommandWrapper(host1,"NAMENODE"));
  Assert.assertNotNull(stage1.getExecutionCommandWrapper(host1,"DATANODE"));
  Assert.assertNotNull(stage1.getExecutionCommandWrapper(host2,"NAMENODE"));
  Assert.assertNotNull(stage2.getExecutionCommandWrapper(host1,"HBASE_MASTER"));
  Assert.assertNull(stage1.getExecutionCommandWrapper(host2,"DATANODE"));
  Assert.assertNotNull(stage3.getExecutionCommandWrapper(host1,"HBASE_SERVICE_CHECK"));
  Assert.assertNotNull(stage2.getExecutionCommandWrapper(host2,"HDFS_SERVICE_CHECK"));
  sch1.setState(State.STARTED);
  sch2.setState(State.STARTED);
  sch3.setState(State.STARTED);
  sch4.setState(State.STARTED);
  sch5.setState(State.STARTED);
  reqs.clear();
  req1=new ServiceRequest(clusterName,serviceName1,null,State.STARTED.toString());
  req2=new ServiceRequest(clusterName,serviceName2,null,State.STARTED.toString());
  reqs.add(req1);
  reqs.add(req2);
  trackAction=controller.updateServices(reqs);
  Assert.assertNull(trackAction);
}
