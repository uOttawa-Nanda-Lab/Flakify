@Test public void testSpringAuthenticationProviderAsDefaultConfiguration() throws Exception {
  this.context=new AnnotationConfigWebApplicationContext();
  this.context.setServletContext(new MockServletContext());
  this.context.register(ManagementServerPropertiesAutoConfiguration.class);
  this.context.register(SecurityAutoConfiguration.class);
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
