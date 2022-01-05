@Test public void testSshConfiguration(){
  MockEnvironment env=new MockEnvironment();
  env.setProperty("shell.ssh.enabled","true");
  env.setProperty("shell.ssh.port","3333");
  this.context=new AnnotationConfigWebApplicationContext();
  this.context.setEnvironment(env);
  this.context.register(CrshAutoConfiguration.class);
  this.context.refresh();
  PluginLifeCycle lifeCycle=this.context.getBean(PluginLifeCycle.class);
  assertEquals("3333",lifeCycle.getConfig().getProperty("crash.ssh.port"));
}
