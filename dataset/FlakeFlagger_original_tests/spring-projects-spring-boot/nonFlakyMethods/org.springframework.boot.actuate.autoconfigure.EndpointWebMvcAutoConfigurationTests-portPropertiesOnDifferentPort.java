@Test public void portPropertiesOnDifferentPort() throws Exception {
  new ServerPortInfoApplicationContextInitializer().initialize(this.applicationContext);
  this.applicationContext.register(RootConfig.class,DifferentPortConfig.class,BaseConfiguration.class,EndpointWebMvcAutoConfiguration.class,ErrorMvcAutoConfiguration.class);
  this.applicationContext.refresh();
  Integer localServerPort=this.applicationContext.getEnvironment().getProperty("local.server.port",Integer.class);
  Integer localManagementPort=this.applicationContext.getEnvironment().getProperty("local.management.port",Integer.class);
  assertThat(localServerPort,notNullValue());
  assertThat(localManagementPort,notNullValue());
  assertThat(localServerPort,not(equalTo(localManagementPort)));
  assertThat(this.applicationContext.getBean(ServerPortConfig.class).getCount(),equalTo(2));
  this.applicationContext.close();
  assertAllClosed();
}
