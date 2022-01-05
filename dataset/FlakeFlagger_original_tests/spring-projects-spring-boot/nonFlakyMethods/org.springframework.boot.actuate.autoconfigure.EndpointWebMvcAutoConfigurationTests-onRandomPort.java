@Test public void onRandomPort() throws Exception {
  this.applicationContext.register(RootConfig.class,RandomPortConfig.class,BaseConfiguration.class,EndpointWebMvcAutoConfiguration.class,ErrorMvcAutoConfiguration.class);
  GrabManagementPort grabManagementPort=new GrabManagementPort(this.applicationContext);
  this.applicationContext.addApplicationListener(grabManagementPort);
  this.applicationContext.refresh();
  int managementPort=grabManagementPort.getServletContainer().getPort();
  assertThat(managementPort,not(equalTo(ports.get().server)));
  assertContent("/controller",ports.get().server,"controlleroutput");
  assertContent("/endpoint",ports.get().server,null);
  assertContent("/controller",managementPort,null);
  assertContent("/endpoint",managementPort,"endpointoutput");
}
