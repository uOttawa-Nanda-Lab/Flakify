@Test public void testConvertToResponse() throws AmbariException {
  String componentName="NAMENODE";
  ServiceComponent component=serviceComponentFactory.createNew(service,componentName);
  service.addServiceComponent(component);
  component.persist();
  ServiceComponent sc=service.getServiceComponent(componentName);
  Assert.assertNotNull(sc);
  sc.setDesiredState(State.INSTALLED);
  sc.setDesiredStackVersion(new StackId("HDP-1.0.0"));
  ServiceComponentResponse r=sc.convertToResponse();
  Assert.assertEquals(sc.getClusterName(),r.getClusterName());
  Assert.assertEquals(sc.getClusterId(),r.getClusterId().longValue());
  Assert.assertEquals(sc.getName(),r.getComponentName());
  Assert.assertEquals(sc.getServiceName(),r.getServiceName());
  Assert.assertEquals(sc.getDesiredStackVersion().getStackId(),r.getDesiredStackVersion());
  Assert.assertEquals(sc.getDesiredState().toString(),r.getDesiredState());
  StringBuilder sb=new StringBuilder();
  sc.debugDump(sb);
  Assert.assertFalse(sb.toString().isEmpty());
}
