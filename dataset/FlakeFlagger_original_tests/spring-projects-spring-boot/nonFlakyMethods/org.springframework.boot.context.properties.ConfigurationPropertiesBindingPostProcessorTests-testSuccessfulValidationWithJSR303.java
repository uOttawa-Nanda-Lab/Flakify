@Test public void testSuccessfulValidationWithJSR303(){
  MockEnvironment env=new MockEnvironment();
  env.setProperty("test.foo","123456");
  env.setProperty("test.bar","654321");
  this.context=new AnnotationConfigApplicationContext();
  this.context.setEnvironment(env);
  this.context.register(TestConfigurationWithJSR303.class);
  this.context.refresh();
}
