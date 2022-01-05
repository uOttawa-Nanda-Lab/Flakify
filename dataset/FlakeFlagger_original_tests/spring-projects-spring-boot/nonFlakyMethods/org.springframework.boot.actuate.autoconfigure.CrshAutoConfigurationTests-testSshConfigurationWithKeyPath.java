@Test public void testSshConfigurationWithKeyPath(){
  MockEnvironment env=new MockEnvironment();
  env.setProperty("shell.ssh.enabled","true");
  env.setProperty("shell.ssh.key_path","~/.ssh/id.pem");
  this.context=new AnnotationConfigWebApplicationContext();
  this.context.setEnvironment(env);
  this.context.register(CrshAutoConfiguration.class);
  this.context.refresh();
  PluginLifeCycle lifeCycle=this.context.getBean(PluginLifeCycle.class);
  assertEquals("~/.ssh/id.pem",lifeCycle.getConfig().getProperty("crash.ssh.keypath"));
}
