@Test public void testCloudFoundry(){
  ConfigurableApplicationContext context=new AnnotationConfigApplicationContext();
  EnvironmentTestUtils.addEnvironment(context,"spring.config.name:foo","PORT:8080","vcap.application.name:bar","vcap.application.instance_index:2");
  this.initializer.initialize(context);
  assertEquals("bar:2",context.getId());
}
