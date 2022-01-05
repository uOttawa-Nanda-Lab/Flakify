@Test public void specificPortsViaProperties() throws Exception {
  EnvironmentTestUtils.addEnvironment(this.applicationContext,"server.port:" + ports.get().server,"management.port:" + ports.get().management);
  this.applicationContext.register(RootConfig.class,BaseConfiguration.class,EndpointWebMvcAutoConfiguration.class,ErrorMvcAutoConfiguration.class);
  this.applicationContext.refresh();
  assertContent("/controller",ports.get().server,"controlleroutput");
  assertContent("/endpoint",ports.get().server,null);
  assertContent("/controller",ports.get().management,null);
  assertContent("/endpoint",ports.get().management,"endpointoutput");
  this.applicationContext.close();
  assertAllClosed();
}
