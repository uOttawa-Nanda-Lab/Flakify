@Test public void testNameAndPort(){
  ConfigurableApplicationContext context=new AnnotationConfigApplicationContext();
  EnvironmentTestUtils.addEnvironment(context,"spring.application.name:foo","PORT:8080");
  this.initializer.initialize(context);
  assertEquals("foo:8080",context.getId());
}
