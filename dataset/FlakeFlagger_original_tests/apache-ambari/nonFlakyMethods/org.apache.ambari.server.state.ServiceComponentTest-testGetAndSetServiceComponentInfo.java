@Test public void testGetAndSetServiceComponentInfo() throws AmbariException {
  String componentName="NAMENODE";
  ServiceComponent component=serviceComponentFactory.createNew(service,componentName);
  service.addServiceComponent(component);
  component.persist();
  ServiceComponent sc=service.getServiceComponent(componentName);
  Assert.assertNotNull(sc);
  sc.setDesiredState(State.INSTALLED);
  Assert.assertEquals(State.INSTALLED,sc.getDesiredState());
  sc.setDesiredStackVersion(new StackId("HDP-1.0.0"));
  Assert.assertEquals("HDP-1.0.0",sc.getDesiredStackVersion().getStackId());
  ServiceComponentDesiredStateDAO serviceComponentDesiredStateDAO=injector.getInstance(ServiceComponentDesiredStateDAO.class);
  ServiceComponentDesiredStateEntityPK primaryKey=new ServiceComponentDesiredStateEntityPK();
  primaryKey.setClusterId(cluster.getClusterId());
  primaryKey.setComponentName(componentName);
  primaryKey.setServiceName(serviceName);
  ServiceComponentDesiredStateEntity serviceComponentDesiredStateEntity=serviceComponentDesiredStateDAO.findByPK(primaryKey);
  ServiceComponent sc1=serviceComponentFactory.createExisting(service,serviceComponentDesiredStateEntity);
  Assert.assertNotNull(sc1);
  Assert.assertEquals(State.INSTALLED,sc1.getDesiredState());
  Assert.assertEquals("HDP-1.0.0",sc1.getDesiredStackVersion().getStackId());
}
