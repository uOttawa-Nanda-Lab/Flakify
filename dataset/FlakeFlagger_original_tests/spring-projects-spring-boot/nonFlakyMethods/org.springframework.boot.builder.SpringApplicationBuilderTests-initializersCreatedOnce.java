@Test public void initializersCreatedOnce() throws Exception {
  SpringApplicationBuilder application=new SpringApplicationBuilder(ExampleConfig.class).web(false);
  this.context=application.run();
  assertEquals(2,application.application().getInitializers().size());
}
