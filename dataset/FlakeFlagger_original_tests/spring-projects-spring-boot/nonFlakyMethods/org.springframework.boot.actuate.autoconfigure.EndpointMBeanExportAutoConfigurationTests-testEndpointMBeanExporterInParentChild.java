@Test public void testEndpointMBeanExporterInParentChild() throws IntrospectionException, InstanceNotFoundException, MalformedObjectNameException, ReflectionException {
  this.context=new AnnotationConfigApplicationContext();
  this.context.register(JmxAutoConfiguration.class,EndpointAutoConfiguration.class,EndpointMBeanExportAutoConfiguration.class);
  AnnotationConfigApplicationContext parent=new AnnotationConfigApplicationContext();
  parent.register(JmxAutoConfiguration.class,EndpointAutoConfiguration.class,EndpointMBeanExportAutoConfiguration.class);
  this.context.setParent(parent);
  parent.refresh();
  this.context.refresh();
  parent.close();
  System.out.println("parent " + ObjectUtils.getIdentityHexString(parent));
  System.out.println("child " + ObjectUtils.getIdentityHexString(this.context));
}
