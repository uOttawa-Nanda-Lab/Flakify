@Test @SuppressWarnings("unchecked") public void deviceResolverHandlerInterceptorRegistered() throws Exception {
  AnnotationConfigEmbeddedWebApplicationContext context=new AnnotationConfigEmbeddedWebApplicationContext();
  context.register(Config.class,WebMvcAutoConfiguration.class,HttpMessageConvertersAutoConfiguration.class,DeviceResolverAutoConfiguration.class,PropertyPlaceholderAutoConfiguration.class);
  context.refresh();
  RequestMappingHandlerMapping mapping=(RequestMappingHandlerMapping)context.getBean("requestMappingHandlerMapping");
  Field interceptorsField=ReflectionUtils.findField(RequestMappingHandlerMapping.class,"interceptors");
  interceptorsField.setAccessible(true);
  List<Object> interceptors=(List<Object>)ReflectionUtils.getField(interceptorsField,mapping);
  context.close();
  for (  Object o : interceptors) {
    if (o instanceof DeviceResolverHandlerInterceptor) {
      return;
    }
  }
  fail("DeviceResolverHandlerInterceptor was not registered.");
}
