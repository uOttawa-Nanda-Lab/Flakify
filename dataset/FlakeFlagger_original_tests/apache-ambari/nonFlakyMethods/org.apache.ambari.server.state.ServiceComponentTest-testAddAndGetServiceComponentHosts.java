@Test public void testAddAndGetServiceComponentHosts() throws AmbariException {
  String componentName="NAMENODE";
  ServiceComponent component=serviceComponentFactory.createNew(service,componentName);
  service.addServiceComponent(component);
  component.persist();
  ServiceComponent sc=service.getServiceComponent(componentName);
  Assert.assertNotNull(sc);
  Assert.assertTrue(sc.getServiceComponentHosts().isEmpty());
  try {
    serviceComponentHostFactory.createNew(sc,"h1",false);
    fail("Expected error for invalid host");
  }
 catch (  Exception e) {
  }
  addHostToCluster("h1",service.getCluster().getClusterName());
  addHostToCluster("h2",service.getCluster().getClusterName());
  addHostToCluster("h3",service.getCluster().getClusterName());
  ServiceComponentHost sch1=serviceComponentHostFactory.createNew(sc,"h1",false);
  ServiceComponentHost sch2=serviceComponentHostFactory.createNew(sc,"h2",false);
  ServiceComponentHost failSch=serviceComponentHostFactory.createNew(sc,"h2",false);
  Map<String,ServiceComponentHost> compHosts=new HashMap<String,ServiceComponentHost>();
  compHosts.put("h1",sch1);
  compHosts.put("h2",sch2);
  compHosts.put("h3",failSch);
  try {
    sc.addServiceComponentHosts(compHosts);
    fail("Expected error for dups");
  }
 catch (  Exception e) {
  }
  Assert.assertTrue(sc.getServiceComponentHosts().isEmpty());
  compHosts.remove("h3");
  sc.addServiceComponentHosts(compHosts);
  Assert.assertEquals(2,sc.getServiceComponentHosts().size());
  sch1.persist();
  sch2.persist();
  ServiceComponentHost schCheck=sc.getServiceComponentHost("h2");
  Assert.assertNotNull(schCheck);
  Assert.assertEquals("h2",schCheck.getHostName());
  ServiceComponentHost sch3=serviceComponentHostFactory.createNew(sc,"h3",false);
  sc.addServiceComponentHost(sch3);
  sch3.persist();
  Assert.assertNotNull(sc.getServiceComponentHost("h3"));
  sch1.setDesiredStackVersion(new StackId("HDP-1.1.0"));
  sch1.setState(State.STARTING);
  sch1.setStackVersion(new StackId("HDP-1.0.0"));
  sch1.setDesiredState(State.STARTED);
  HostComponentDesiredStateDAO desiredStateDAO=injector.getInstance(HostComponentDesiredStateDAO.class);
  HostComponentStateDAO liveStateDAO=injector.getInstance(HostComponentStateDAO.class);
  HostComponentDesiredStateEntityPK dPK=new HostComponentDesiredStateEntityPK();
  HostComponentStateEntityPK lPK=new HostComponentStateEntityPK();
  dPK.setClusterId(cluster.getClusterId());
  dPK.setComponentName(componentName);
  dPK.setHostName("h1");
  dPK.setServiceName(serviceName);
  lPK.setClusterId(cluster.getClusterId());
  lPK.setComponentName(componentName);
  lPK.setHostName("h1");
  lPK.setServiceName(serviceName);
  HostComponentDesiredStateEntity desiredStateEntity=desiredStateDAO.findByPK(dPK);
  HostComponentStateEntity stateEntity=liveStateDAO.findByPK(lPK);
  ServiceComponentHost sch=serviceComponentHostFactory.createExisting(sc,stateEntity,desiredStateEntity);
  Assert.assertNotNull(sch);
  Assert.assertEquals(State.STARTING,sch.getState());
  Assert.assertEquals(State.STARTED,sch.getDesiredState());
  Assert.assertEquals("HDP-1.0.0",sch.getStackVersion().getStackId());
  Assert.assertEquals("HDP-1.1.0",sch.getDesiredStackVersion().getStackId());
}
