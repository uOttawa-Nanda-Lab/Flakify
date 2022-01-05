@Test public void onDifferentPort() throws Exception {
  this.applicationContext.register(RootConfig.class,DifferentPortConfig.class,BaseConfiguration.class,EndpointWebMvcAutoConfiguration.class,ErrorMvcAutoConfiguration.class);
  this.applicationContext.refresh();
  assertContent("/controller",ports.get().server,"controlleroutput");
  assertContent("/endpoint",ports.get().server,null);
  assertContent("/controller",ports.get().management,null);
  assertContent("/endpoint",ports.get().management,"endpointoutput");
  this.applicationContext.close();
  assertAllClosed();
}
