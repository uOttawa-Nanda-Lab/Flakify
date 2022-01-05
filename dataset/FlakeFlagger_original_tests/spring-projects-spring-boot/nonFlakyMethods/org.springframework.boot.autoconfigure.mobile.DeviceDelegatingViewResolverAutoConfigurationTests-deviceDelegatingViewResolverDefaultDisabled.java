@Test(expected=NoSuchBeanDefinitionException.class) public void deviceDelegatingViewResolverDefaultDisabled() throws Exception {
  this.context=new AnnotationConfigEmbeddedWebApplicationContext();
  this.context.register(Config.class,DeviceDelegatingViewResolverConfiguration.class);
  this.context.refresh();
  this.context.getBean("deviceDelegatingViewResolver",AbstractDeviceDelegatingViewResolver.class);
}
