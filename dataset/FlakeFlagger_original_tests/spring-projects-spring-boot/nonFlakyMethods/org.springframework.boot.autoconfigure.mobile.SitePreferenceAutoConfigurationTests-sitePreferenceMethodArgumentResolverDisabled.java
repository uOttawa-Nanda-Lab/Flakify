@Test(expected=NoSuchBeanDefinitionException.class) public void sitePreferenceMethodArgumentResolverDisabled(){
  this.context=new AnnotationConfigWebApplicationContext();
  EnvironmentTestUtils.addEnvironment(this.context,"spring.mobile.sitepreference.enabled:false");
  this.context.register(SitePreferenceAutoConfiguration.class);
  this.context.refresh();
  this.context.getBean(SitePreferenceHandlerMethodArgumentResolver.class);
}
