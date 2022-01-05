@Test public void testCustomShellProperties() throws Exception {
  MockEnvironment env=new MockEnvironment();
  env.setProperty("shell.auth","simple");
  AnnotationConfigWebApplicationContext context=new AnnotationConfigWebApplicationContext();
  context.setEnvironment(env);
  context.setServletContext(new MockServletContext());
  context.register(TestShellConfiguration.class);
  context.register(CrshAutoConfiguration.class);
  context.refresh();
  PluginLifeCycle lifeCycle=context.getBean(PluginLifeCycle.class);
  String uuid=lifeCycle.getConfig().getProperty("test.uuid");
  assertEquals(TestShellConfiguration.uuid,uuid);
  context.close();
}
