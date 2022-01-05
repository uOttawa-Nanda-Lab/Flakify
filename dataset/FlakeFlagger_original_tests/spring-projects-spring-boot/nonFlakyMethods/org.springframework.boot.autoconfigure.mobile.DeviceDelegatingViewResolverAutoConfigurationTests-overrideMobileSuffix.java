@Test public void overrideMobileSuffix() throws Exception {
  this.context=new AnnotationConfigEmbeddedWebApplicationContext();
  EnvironmentTestUtils.addEnvironment(this.context,"spring.mobile.devicedelegatingviewresolver.enabled:true","spring.mobile.devicedelegatingviewresolver.mobileSuffix:.mob");
  this.context.register(Config.class,WebMvcAutoConfiguration.class,HttpMessageConvertersAutoConfiguration.class,PropertyPlaceholderAutoConfiguration.class,DeviceDelegatingViewResolverConfiguration.class);
  this.context.refresh();
  LiteDeviceDelegatingViewResolver liteDeviceDelegatingViewResolver=this.context.getBean("deviceDelegatingViewResolver",LiteDeviceDelegatingViewResolver.class);
  Field mobileSuffixField=ReflectionUtils.findField(LiteDeviceDelegatingViewResolver.class,"mobileSuffix");
  mobileSuffixField.setAccessible(true);
  String mobileSuffix=(String)ReflectionUtils.getField(mobileSuffixField,liteDeviceDelegatingViewResolver);
  assertEquals(".mob",mobileSuffix);
}
