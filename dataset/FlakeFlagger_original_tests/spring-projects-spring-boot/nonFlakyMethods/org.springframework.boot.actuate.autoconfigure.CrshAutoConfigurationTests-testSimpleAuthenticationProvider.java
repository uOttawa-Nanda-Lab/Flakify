@Test public void testSimpleAuthenticationProvider() throws Exception {
  MockEnvironment env=new MockEnvironment();
  env.setProperty("shell.auth","simple");
  env.setProperty("shell.auth.simple.user.name","user");
  env.setProperty("shell.auth.simple.user.password","password");
  this.context=new AnnotationConfigWebApplicationContext();
  this.context.setEnvironment(env);
  this.context.setServletContext(new MockServletContext());
  this.context.register(SecurityConfiguration.class);
  this.context.register(CrshAutoConfiguration.class);
  this.context.refresh();
  PluginLifeCycle lifeCycle=this.context.getBean(PluginLifeCycle.class);
  assertEquals("simple",lifeCycle.getConfig().get("crash.auth"));
  AuthenticationPlugin<String> authenticationPlugin=null;
  String authentication=lifeCycle.getConfig().getProperty("crash.auth");
  assertNotNull(authentication);
  for (  AuthenticationPlugin plugin : lifeCycle.getContext().getPlugins(AuthenticationPlugin.class)) {
    if (authentication.equals(plugin.getName())) {
      authenticationPlugin=plugin;
      break;
    }
  }
  assertNotNull(authenticationPlugin);
  assertTrue(authenticationPlugin.authenticate("user","password"));
  assertFalse(authenticationPlugin.authenticate(UUID.randomUUID().toString(),"password"));
}
