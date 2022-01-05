@Test public void onSamePort() throws Exception {
  this.applicationContext.register(RootConfig.class,BaseConfiguration.class,ServerPortConfig.class,EndpointWebMvcAutoConfiguration.class);
  this.applicationContext.refresh();
  assertContent("/controller",ports.get().server,"controlleroutput");
  assertContent("/endpoint",ports.get().server,"endpointoutput");
  assertContent("/controller",ports.get().management,null);
  assertContent("/endpoint",ports.get().management,null);
  assertTrue(hasHeader("/endpoint",ports.get().server,"X-Application-Context"));
  this.applicationContext.close();
  assertAllClosed();
}
