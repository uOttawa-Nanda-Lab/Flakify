@Test public void testDisabledPlugins() throws Exception {
  MockEnvironment env=new MockEnvironment();
  env.setProperty("shell.disabled_plugins","GroovyREPL, termIOHandler, org.crsh.auth.AuthenticationPlugin");
  this.context=new AnnotationConfigWebApplicationContext();
  this.context.setEnvironment(env);
  this.context.register(CrshAutoConfiguration.class);
  this.context.refresh();
  PluginLifeCycle lifeCycle=this.context.getBean(PluginLifeCycle.class);
  assertNotNull(lifeCycle);
  assertNull(lifeCycle.getContext().getPlugin(GroovyRepl.class));
  assertNull(lifeCycle.getContext().getPlugin(ProcessorIOHandler.class));
  assertNull(lifeCycle.getContext().getPlugin(JaasAuthenticationPlugin.class));
}
