@Test public void sitePreferenceMethodArgumentResolverCreated() throws Exception {
  this.context=new AnnotationConfigWebApplicationContext();
  this.context.register(SitePreferenceAutoConfiguration.class);
  this.context.refresh();
  assertNotNull(this.context.getBean(SitePreferenceHandlerMethodArgumentResolver.class));
}
