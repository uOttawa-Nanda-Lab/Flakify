@Test public void sitePreferenceHandlerInterceptorCreated(){
  this.context=new AnnotationConfigWebApplicationContext();
  this.context.register(SitePreferenceAutoConfiguration.class);
  this.context.refresh();
  assertNotNull(this.context.getBean(SitePreferenceHandlerInterceptor.class));
}
