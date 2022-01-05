@Test public void testDefaultDomainConfiguredOnMBeanExport(){
  MockEnvironment env=new MockEnvironment();
  env.setProperty("spring.jmx.enabled","true");
  env.setProperty("spring.jmx.default_domain","my-test-domain");
  this.context=new AnnotationConfigApplicationContext();
  this.context.setEnvironment(env);
  this.context.register(TestConfiguration.class,JmxAutoConfiguration.class);
  this.context.refresh();
  MBeanExporter mBeanExporter=this.context.getBean(MBeanExporter.class);
  assertNotNull(mBeanExporter);
  MetadataNamingStrategy naming=(MetadataNamingStrategy)ReflectionTestUtils.getField(mBeanExporter,"metadataNamingStrategy");
  assertEquals("my-test-domain",ReflectionTestUtils.getField(naming,"defaultDomain"));
}
