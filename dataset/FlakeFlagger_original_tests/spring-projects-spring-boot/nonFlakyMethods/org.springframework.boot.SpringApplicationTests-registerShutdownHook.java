@Test public void registerShutdownHook() throws Exception {
  SpringApplication application=new SpringApplication(ExampleConfig.class);
  application.setApplicationContextClass(SpyApplicationContext.class);
  this.context=application.run();
  SpyApplicationContext applicationContext=(SpyApplicationContext)this.context;
  verify(applicationContext.getApplicationContext()).registerShutdownHook();
}
