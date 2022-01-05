@Test public void overrideTabletPrefix() throws Exception {
  this.context=new AnnotationConfigEmbeddedWebApplicationContext();
  EnvironmentTestUtils.addEnvironment(this.context,"spring.mobile.devicedelegatingviewresolver.enabled:true","spring.mobile.devicedelegatingviewresolver.tabletPrefix:tab/");
  this.context.register(Config.class,WebMvcAutoConfiguration.class,HttpMessageConvertersAutoConfiguration.class,PropertyPlaceholderAutoConfiguration.class,DeviceDelegatingViewResolverConfiguration.class);
  this.context.refresh();
  LiteDeviceDelegatingViewResolver liteDeviceDelegatingViewResolver=this.context.getBean("deviceDelegatingViewResolver",LiteDeviceDelegatingViewResolver.class);
  Field tabletPrefixField=ReflectionUtils.findField(LiteDeviceDelegatingViewResolver.class,"tabletPrefix");
  tabletPrefixField.setAccessible(true);
  String tabletPrefix=(String)ReflectionUtils.getField(tabletPrefixField,liteDeviceDelegatingViewResolver);
  assertEquals("tab/",tabletPrefix);
}
