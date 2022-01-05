@Test public void onSamePortWithoutHeader() throws Exception {
  EnvironmentTestUtils.addEnvironment(this.applicationContext,"management.add-application-context-header:false");
  this.applicationContext.register(RootConfig.class,BaseConfiguration.class,ServerPortConfig.class,EndpointWebMvcAutoConfiguration.class);
  this.applicationContext.refresh();
  assertFalse(hasHeader("/endpoint",ports.get().server,"X-Application-Context"));
  this.applicationContext.close();
  assertAllClosed();
}
