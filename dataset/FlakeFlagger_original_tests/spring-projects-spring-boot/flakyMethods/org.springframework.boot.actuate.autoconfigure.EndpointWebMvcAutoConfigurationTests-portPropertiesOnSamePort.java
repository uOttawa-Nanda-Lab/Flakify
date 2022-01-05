@Test public void portPropertiesOnSamePort() throws Exception {
  this.applicationContext.register(RootConfig.class,BaseConfiguration.class,ServerPortConfig.class,EndpointWebMvcAutoConfiguration.class);
  new ServerPortInfoApplicationContextInitializer().initialize(this.applicationContext);
  this.applicationContext.refresh();
  Integer localServerPort=this.applicationContext.getEnvironment().getProperty("local.server.port",Integer.class);
  Integer localManagementPort=this.applicationContext.getEnvironment().getProperty("local.management.port",Integer.class);
  assertThat(localServerPort,notNullValue());
  assertThat(localManagementPort,notNullValue());
  assertThat(localServerPort,equalTo(localManagementPort));
  this.applicationContext.close();
  assertAllClosed();
}
