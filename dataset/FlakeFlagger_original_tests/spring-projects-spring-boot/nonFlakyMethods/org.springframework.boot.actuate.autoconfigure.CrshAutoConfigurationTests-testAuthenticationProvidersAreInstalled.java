@Test public void testAuthenticationProvidersAreInstalled(){
  this.context=new AnnotationConfigWebApplicationContext();
  this.context.setServletContext(new MockServletContext());
  this.context.register(SecurityConfiguration.class);
  this.context.register(CrshAutoConfiguration.class);
  this.context.refresh();
  PluginLifeCycle lifeCycle=this.context.getBean(PluginLifeCycle.class);
  PluginContext pluginContext=lifeCycle.getContext();
  int count=0;
  Iterator<AuthenticationPlugin> plugins=pluginContext.getPlugins(AuthenticationPlugin.class).iterator();
  while (plugins.hasNext()) {
    count++;
    plugins.next();
  }
  assertEquals(3,count);
}
