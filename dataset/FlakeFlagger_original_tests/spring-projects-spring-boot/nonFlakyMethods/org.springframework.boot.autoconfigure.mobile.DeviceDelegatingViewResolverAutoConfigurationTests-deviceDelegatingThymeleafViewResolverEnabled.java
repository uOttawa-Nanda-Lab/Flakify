@Test public void deviceDelegatingThymeleafViewResolverEnabled() throws Exception {
  this.context=new AnnotationConfigEmbeddedWebApplicationContext();
  EnvironmentTestUtils.addEnvironment(this.context,"spring.mobile.devicedelegatingviewresolver.enabled:true");
  this.context.register(Config.class,WebMvcAutoConfiguration.class,ThymeleafAutoConfiguration.class,HttpMessageConvertersAutoConfiguration.class,PropertyPlaceholderAutoConfiguration.class,DeviceDelegatingViewResolverConfiguration.class);
  this.context.refresh();
  ThymeleafViewResolver thymeleafViewResolver=this.context.getBean(ThymeleafViewResolver.class);
  AbstractDeviceDelegatingViewResolver deviceDelegatingViewResolver=this.context.getBean("deviceDelegatingViewResolver",AbstractDeviceDelegatingViewResolver.class);
  assertNotNull(thymeleafViewResolver);
  assertNotNull(deviceDelegatingViewResolver);
  assertTrue(deviceDelegatingViewResolver.getViewResolver() instanceof ThymeleafViewResolver);
  assertNotNull(this.context.getBean(InternalResourceViewResolver.class));
  assertNotNull(this.context.getBean(ThymeleafViewResolver.class));
  assertTrue(deviceDelegatingViewResolver.getOrder() == thymeleafViewResolver.getOrder() - 1);
}
