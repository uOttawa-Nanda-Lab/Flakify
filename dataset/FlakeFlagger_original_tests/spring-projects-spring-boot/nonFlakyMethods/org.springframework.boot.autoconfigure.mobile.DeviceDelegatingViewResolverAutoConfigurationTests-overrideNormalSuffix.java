@Test public void overrideNormalSuffix() throws Exception {
  this.context=new AnnotationConfigEmbeddedWebApplicationContext();
  EnvironmentTestUtils.addEnvironment(this.context,"spring.mobile.devicedelegatingviewresolver.enabled:true","spring.mobile.devicedelegatingviewresolver.normalSuffix:.nor");
  this.context.register(Config.class,WebMvcAutoConfiguration.class,HttpMessageConvertersAutoConfiguration.class,PropertyPlaceholderAutoConfiguration.class,DeviceDelegatingViewResolverConfiguration.class);
  this.context.refresh();
  LiteDeviceDelegatingViewResolver liteDeviceDelegatingViewResolver=this.context.getBean("deviceDelegatingViewResolver",LiteDeviceDelegatingViewResolver.class);
  Field normalSuffixField=ReflectionUtils.findField(LiteDeviceDelegatingViewResolver.class,"normalSuffix");
  normalSuffixField.setAccessible(true);
  String normalSuffix=(String)ReflectionUtils.getField(normalSuffixField,liteDeviceDelegatingViewResolver);
  assertEquals(".nor",normalSuffix);
}
