@Test public void overrideNormalPrefix() throws Exception {
  this.context=new AnnotationConfigEmbeddedWebApplicationContext();
  EnvironmentTestUtils.addEnvironment(this.context,"spring.mobile.devicedelegatingviewresolver.enabled:true","spring.mobile.devicedelegatingviewresolver.normalPrefix:normal/");
  this.context.register(Config.class,WebMvcAutoConfiguration.class,HttpMessageConvertersAutoConfiguration.class,PropertyPlaceholderAutoConfiguration.class,DeviceDelegatingViewResolverConfiguration.class);
  this.context.refresh();
  LiteDeviceDelegatingViewResolver liteDeviceDelegatingViewResolver=this.context.getBean("deviceDelegatingViewResolver",LiteDeviceDelegatingViewResolver.class);
  Field normalPrefixField=ReflectionUtils.findField(LiteDeviceDelegatingViewResolver.class,"normalPrefix");
  normalPrefixField.setAccessible(true);
  String normalPrefix=(String)ReflectionUtils.getField(normalPrefixField,liteDeviceDelegatingViewResolver);
  assertEquals("normal/",normalPrefix);
}
