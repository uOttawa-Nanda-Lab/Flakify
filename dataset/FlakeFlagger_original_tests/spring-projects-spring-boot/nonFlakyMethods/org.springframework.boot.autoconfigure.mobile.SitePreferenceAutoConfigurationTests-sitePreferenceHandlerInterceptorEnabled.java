@Test public void sitePreferenceHandlerInterceptorEnabled() throws Exception {
  this.context=new AnnotationConfigWebApplicationContext();
  EnvironmentTestUtils.addEnvironment(this.context,"spring.mobile.sitepreference.enabled:true");
  this.context.register(SitePreferenceAutoConfiguration.class);
  this.context.refresh();
  assertNotNull(this.context.getBean(SitePreferenceHandlerInterceptor.class));
}
