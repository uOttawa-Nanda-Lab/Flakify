@Test public void noLinkedInBeanCreatedIfPropertiesArentSet() throws Exception {
  this.context=new AnnotationConfigWebApplicationContext();
  this.context.register(LinkedInAutoConfiguration.class);
  this.context.register(SocialWebAutoConfiguration.class);
  this.context.refresh();
  assertNoConnectionFrameworkBeans();
  assertMissingBean(LinkedIn.class);
}
