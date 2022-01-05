@Test public void initializersCreatedOnceForChild() throws Exception {
  SpringApplicationBuilder application=new SpringApplicationBuilder(ExampleConfig.class).child(ChildConfig.class).web(false);
  this.context=application.run();
  assertEquals(3,application.application().getInitializers().size());
}
