@Test public void parentFirstCreation() throws Exception {
  SpringApplicationBuilder application=new SpringApplicationBuilder(ExampleConfig.class).child(ChildConfig.class);
  application.contextClass(SpyApplicationContext.class);
  this.context=application.run();
  verify(((SpyApplicationContext)this.context).getApplicationContext()).setParent(any(ApplicationContext.class));
  assertThat(((SpyApplicationContext)this.context).getRegisteredShutdownHook(),equalTo(false));
}
