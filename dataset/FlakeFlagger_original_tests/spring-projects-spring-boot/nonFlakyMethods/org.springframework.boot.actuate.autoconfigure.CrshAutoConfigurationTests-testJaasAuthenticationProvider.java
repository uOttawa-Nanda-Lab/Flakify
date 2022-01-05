@Test public void testJaasAuthenticationProvider(){
  MockEnvironment env=new MockEnvironment();
  env.setProperty("shell.auth","jaas");
  env.setProperty("shell.auth.jaas.domain","my-test-domain");
  this.context=new AnnotationConfigWebApplicationContext();
  this.context.setEnvironment(env);
  this.context.setServletContext(new MockServletContext());
  this.context.register(SecurityConfiguration.class);
  this.context.register(CrshAutoConfiguration.class);
  this.context.refresh();
  PluginLifeCycle lifeCycle=this.context.getBean(PluginLifeCycle.class);
  assertEquals("jaas",lifeCycle.getConfig().get("crash.auth"));
  assertEquals("my-test-domain",lifeCycle.getConfig().get("crash.auth.jaas.domain"));
}
