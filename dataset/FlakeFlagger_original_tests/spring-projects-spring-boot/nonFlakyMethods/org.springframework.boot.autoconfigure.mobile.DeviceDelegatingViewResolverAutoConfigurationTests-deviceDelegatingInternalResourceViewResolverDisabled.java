@Test(expected=NoSuchBeanDefinitionException.class) public void deviceDelegatingInternalResourceViewResolverDisabled() throws Exception {
  this.context=new AnnotationConfigEmbeddedWebApplicationContext();
  EnvironmentTestUtils.addEnvironment(this.context,"spring.mobile.devicedelegatingviewresolver.enabled:false");
  this.context.register(Config.class,WebMvcAutoConfiguration.class,HttpMessageConvertersAutoConfiguration.class,PropertyPlaceholderAutoConfiguration.class,DeviceDelegatingViewResolverConfiguration.class);
  this.context.refresh();
  assertNotNull(this.context.getBean(InternalResourceViewResolver.class));
  try {
    this.context.getBean(ThymeleafViewResolver.class);
  }
 catch (  NoSuchBeanDefinitionException e) {
  }
  this.context.getBean("deviceDelegatingViewResolver",AbstractDeviceDelegatingViewResolver.class);
}
