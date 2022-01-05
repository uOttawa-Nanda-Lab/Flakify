@Test(expected=NoSuchBeanDefinitionException.class) public void deviceDelegatingThymeleafViewResolverDisabled() throws Exception {
  this.context=new AnnotationConfigEmbeddedWebApplicationContext();
  EnvironmentTestUtils.addEnvironment(this.context,"spring.mobile.devicedelegatingviewresolver.enabled:false");
  this.context.register(Config.class,WebMvcAutoConfiguration.class,ThymeleafAutoConfiguration.class,HttpMessageConvertersAutoConfiguration.class,PropertyPlaceholderAutoConfiguration.class,DeviceDelegatingViewResolverConfiguration.class);
  this.context.refresh();
  assertNotNull(this.context.getBean(InternalResourceViewResolver.class));
  assertNotNull(this.context.getBean(ThymeleafViewResolver.class));
  this.context.getBean("deviceDelegatingViewResolver",AbstractDeviceDelegatingViewResolver.class);
}
