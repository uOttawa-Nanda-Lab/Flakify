@Test public void deviceDelegatingInternalResourceViewResolverEnabled() throws Exception {
  this.context=new AnnotationConfigEmbeddedWebApplicationContext();
  EnvironmentTestUtils.addEnvironment(this.context,"spring.mobile.devicedelegatingviewresolver.enabled:true");
  this.context.register(Config.class,WebMvcAutoConfiguration.class,HttpMessageConvertersAutoConfiguration.class,PropertyPlaceholderAutoConfiguration.class,DeviceDelegatingViewResolverConfiguration.class);
  this.context.refresh();
  InternalResourceViewResolver internalResourceViewResolver=this.context.getBean(InternalResourceViewResolver.class);
  AbstractDeviceDelegatingViewResolver deviceDelegatingViewResolver=this.context.getBean("deviceDelegatingViewResolver",AbstractDeviceDelegatingViewResolver.class);
  assertNotNull(internalResourceViewResolver);
  assertNotNull(deviceDelegatingViewResolver);
  assertTrue(deviceDelegatingViewResolver.getViewResolver() instanceof InternalResourceViewResolver);
  try {
    this.context.getBean(ThymeleafViewResolver.class);
  }
 catch (  NoSuchBeanDefinitionException e) {
  }
  assertTrue(deviceDelegatingViewResolver.getOrder() == internalResourceViewResolver.getOrder() - 1);
}
