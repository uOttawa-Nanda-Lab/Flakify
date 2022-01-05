@Test public void disabled() throws Exception {
  this.applicationContext.register(RootConfig.class,DisableConfig.class,BaseConfiguration.class,EndpointWebMvcAutoConfiguration.class);
  this.applicationContext.refresh();
  assertContent("/controller",ports.get().server,"controlleroutput");
  assertContent("/endpoint",ports.get().server,null);
  assertContent("/controller",ports.get().management,null);
  assertContent("/endpoint",ports.get().management,null);
  this.applicationContext.close();
  assertAllClosed();
}
