@Test public void overrideTabletSuffix() throws Exception {
  this.context=new AnnotationConfigEmbeddedWebApplicationContext();
  EnvironmentTestUtils.addEnvironment(this.context,"spring.mobile.devicedelegatingviewresolver.enabled:true","spring.mobile.devicedelegatingviewresolver.tabletSuffix:.tab");
  this.context.register(Config.class,WebMvcAutoConfiguration.class,HttpMessageConvertersAutoConfiguration.class,PropertyPlaceholderAutoConfiguration.class,DeviceDelegatingViewResolverConfiguration.class);
  this.context.refresh();
  LiteDeviceDelegatingViewResolver liteDeviceDelegatingViewResolver=this.context.getBean("deviceDelegatingViewResolver",LiteDeviceDelegatingViewResolver.class);
  Field tabletSuffixField=ReflectionUtils.findField(LiteDeviceDelegatingViewResolver.class,"tabletSuffix");
  tabletSuffixField.setAccessible(true);
  String tabletSuffix=(String)ReflectionUtils.getField(tabletSuffixField,liteDeviceDelegatingViewResolver);
  assertEquals(".tab",tabletSuffix);
}
