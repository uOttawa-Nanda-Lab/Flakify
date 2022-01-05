@Test(expected=NoSuchBeanDefinitionException.class) public void testDisabledMBeanExport(){
  MockEnvironment env=new MockEnvironment();
  env.setProperty("spring.jmx.enabled","false");
  this.context=new AnnotationConfigApplicationContext();
  this.context.setEnvironment(env);
  this.context.register(TestConfiguration.class,JmxAutoConfiguration.class);
  this.context.refresh();
  this.context.getBean(MBeanExporter.class);
}
