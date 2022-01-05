@Test public void contextPath() throws Exception {
  EnvironmentTestUtils.addEnvironment(this.applicationContext,"management.contextPath:/test");
  this.applicationContext.register(RootConfig.class,ServerPortConfig.class,PropertyPlaceholderAutoConfiguration.class,ManagementServerPropertiesAutoConfiguration.class,ServerPropertiesAutoConfiguration.class,EmbeddedServletContainerAutoConfiguration.class,HttpMessageConvertersAutoConfiguration.class,DispatcherServletAutoConfiguration.class,WebMvcAutoConfiguration.class,EndpointWebMvcAutoConfiguration.class);
  this.applicationContext.refresh();
  assertContent("/controller",ports.get().server,"controlleroutput");
  assertContent("/test/endpoint",ports.get().server,"endpointoutput");
  this.applicationContext.close();
  assertAllClosed();
}
