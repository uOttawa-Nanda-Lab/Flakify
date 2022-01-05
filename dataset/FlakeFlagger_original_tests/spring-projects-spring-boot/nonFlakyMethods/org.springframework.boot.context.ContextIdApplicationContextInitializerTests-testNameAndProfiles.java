@Test public void testNameAndProfiles(){
  ConfigurableApplicationContext context=new AnnotationConfigApplicationContext();
  EnvironmentTestUtils.addEnvironment(context,"spring.application.name:foo","spring.profiles.active: spam,bar","spring.application.index:12");
  this.initializer.initialize(context);
  assertEquals("foo:spam,bar:12",context.getId());
}
