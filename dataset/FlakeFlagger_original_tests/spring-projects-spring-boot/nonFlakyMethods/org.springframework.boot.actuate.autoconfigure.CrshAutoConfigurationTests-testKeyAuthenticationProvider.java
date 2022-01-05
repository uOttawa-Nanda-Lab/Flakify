@Test public void testKeyAuthenticationProvider(){
  MockEnvironment env=new MockEnvironment();
  env.setProperty("shell.auth","key");
  env.setProperty("shell.auth.key.path","~/test.pem");
  this.context=new AnnotationConfigWebApplicationContext();
  this.context.setEnvironment(env);
  this.context.setServletContext(new MockServletContext());
  this.context.register(SecurityConfiguration.class);
  this.context.register(CrshAutoConfiguration.class);
  this.context.refresh();
  PluginLifeCycle lifeCycle=this.context.getBean(PluginLifeCycle.class);
  assertEquals("key",lifeCycle.getConfig().get("crash.auth"));
  assertEquals("~/test.pem",lifeCycle.getConfig().get("crash.auth.key.path"));
}
