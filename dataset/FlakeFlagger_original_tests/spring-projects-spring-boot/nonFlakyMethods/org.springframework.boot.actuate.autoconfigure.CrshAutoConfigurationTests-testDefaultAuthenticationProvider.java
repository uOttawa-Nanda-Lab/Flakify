@Test public void testDefaultAuthenticationProvider(){
  MockEnvironment env=new MockEnvironment();
  this.context=new AnnotationConfigWebApplicationContext();
  this.context.setEnvironment(env);
  this.context.setServletContext(new MockServletContext());
  this.context.register(CrshAutoConfiguration.class);
  this.context.refresh();
  PluginLifeCycle lifeCycle=this.context.getBean(PluginLifeCycle.class);
  assertEquals("simple",lifeCycle.getConfig().get("crash.auth"));
}
