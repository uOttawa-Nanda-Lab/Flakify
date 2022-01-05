@Test @SuppressWarnings("unchecked") public void sitePreferenceHandlerInterceptorRegistered() throws Exception {
  AnnotationConfigEmbeddedWebApplicationContext context=new AnnotationConfigEmbeddedWebApplicationContext();
  context.register(Config.class,WebMvcAutoConfiguration.class,HttpMessageConvertersAutoConfiguration.class,SitePreferenceAutoConfiguration.class,PropertyPlaceholderAutoConfiguration.class);
  context.refresh();
  RequestMappingHandlerMapping mapping=(RequestMappingHandlerMapping)context.getBean("requestMappingHandlerMapping");
  Field interceptorsField=ReflectionUtils.findField(RequestMappingHandlerMapping.class,"interceptors");
  interceptorsField.setAccessible(true);
  List<Object> interceptors=(List<Object>)ReflectionUtils.getField(interceptorsField,mapping);
  context.close();
  for (  Object o : interceptors) {
    if (o instanceof SitePreferenceHandlerInterceptor) {
      return;
    }
  }
  fail("SitePreferenceHandlerInterceptor was not registered.");
}
