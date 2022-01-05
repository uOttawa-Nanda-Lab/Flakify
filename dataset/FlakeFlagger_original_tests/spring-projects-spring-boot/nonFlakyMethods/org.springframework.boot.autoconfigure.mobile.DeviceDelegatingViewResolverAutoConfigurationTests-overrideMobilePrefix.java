@Test public void overrideMobilePrefix() throws Exception {
  this.context=new AnnotationConfigEmbeddedWebApplicationContext();
  EnvironmentTestUtils.addEnvironment(this.context,"spring.mobile.devicedelegatingviewresolver.enabled:true","spring.mobile.devicedelegatingviewresolver.mobilePrefix:mob/");
  this.context.register(Config.class,WebMvcAutoConfiguration.class,HttpMessageConvertersAutoConfiguration.class,PropertyPlaceholderAutoConfiguration.class,DeviceDelegatingViewResolverConfiguration.class);
  this.context.refresh();
  LiteDeviceDelegatingViewResolver liteDeviceDelegatingViewResolver=this.context.getBean("deviceDelegatingViewResolver",LiteDeviceDelegatingViewResolver.class);
  Field mobilePrefixField=ReflectionUtils.findField(LiteDeviceDelegatingViewResolver.class,"mobilePrefix");
  mobilePrefixField.setAccessible(true);
  String mobilePrefix=(String)ReflectionUtils.getField(mobilePrefixField,liteDeviceDelegatingViewResolver);
  assertEquals("mob/",mobilePrefix);
}
