@Test public void testInitializersSeeBoundProperties(){
  MockEnvironment env=new MockEnvironment();
  env.setProperty("bar","foo");
  this.context=new AnnotationConfigApplicationContext();
  this.context.setEnvironment(env);
  this.context.register(TestConfigurationWithInitializer.class);
  this.context.refresh();
}
