@Test public void testEnabledMBeanExport(){
  MockEnvironment env=new MockEnvironment();
  env.setProperty("spring.jmx.enabled","true");
  this.context=new AnnotationConfigApplicationContext();
  this.context.setEnvironment(env);
  this.context.register(JmxAutoConfiguration.class);
  this.context.refresh();
  assertNotNull(this.context.getBean(MBeanExporter.class));
}
