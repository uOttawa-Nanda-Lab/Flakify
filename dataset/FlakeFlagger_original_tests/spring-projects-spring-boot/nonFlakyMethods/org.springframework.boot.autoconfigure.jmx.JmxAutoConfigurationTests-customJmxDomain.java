@Test public void customJmxDomain(){
  this.context=new AnnotationConfigApplicationContext();
  this.context.register(CustomJmxDomainConfiguration.class,JmxAutoConfiguration.class,IntegrationAutoConfiguration.class);
  this.context.refresh();
  IntegrationMBeanExporter mbeanExporter=this.context.getBean(IntegrationMBeanExporter.class);
  DirectFieldAccessor dfa=new DirectFieldAccessor(mbeanExporter);
  assertEquals("foo.my",dfa.getPropertyValue("domain"));
}
