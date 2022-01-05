@Test public void parentContextCreation() throws Exception {
  SpringApplicationBuilder application=new SpringApplicationBuilder(ChildConfig.class).contextClass(SpyApplicationContext.class);
  application.parent(ExampleConfig.class);
  this.context=application.run();
  verify(((SpyApplicationContext)this.context).getApplicationContext()).setParent(any(ApplicationContext.class));
  assertThat(((SpyApplicationContext)this.context).getRegisteredShutdownHook(),equalTo(false));
}
