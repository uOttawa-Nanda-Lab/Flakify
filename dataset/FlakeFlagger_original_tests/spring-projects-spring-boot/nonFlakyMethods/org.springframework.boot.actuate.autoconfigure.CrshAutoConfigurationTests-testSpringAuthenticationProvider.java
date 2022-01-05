@Test public void testSpringAuthenticationProvider() throws Exception {
  MockEnvironment env=new MockEnvironment();
  env.setProperty("shell.auth","spring");
  this.context=new AnnotationConfigWebApplicationContext();
  this.context.setEnvironment(env);
  this.context.setServletContext(new MockServletContext());
  this.context.register(SecurityConfiguration.class);
  this.context.register(CrshAutoConfiguration.class);
  this.context.refresh();
  PluginLifeCycle lifeCycle=this.context.getBean(PluginLifeCycle.class);
  AuthenticationPlugin<String> authenticationPlugin=null;
  String authentication=lifeCycle.getConfig().getProperty("crash.auth");
  assertNotNull(authentication);
  for (  AuthenticationPlugin plugin : lifeCycle.getContext().getPlugins(AuthenticationPlugin.class)) {
    if (authentication.equals(plugin.getName())) {
      authenticationPlugin=plugin;
      break;
    }
  }
  assertTrue(authenticationPlugin.authenticate(SecurityConfiguration.USERNAME,SecurityConfiguration.PASSWORD));
  assertFalse(authenticationPlugin.authenticate(UUID.randomUUID().toString(),SecurityConfiguration.PASSWORD));
}
