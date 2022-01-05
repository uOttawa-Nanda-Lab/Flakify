@Test public void noFacebookBeanCreatedWhenPropertiesArentSet() throws Exception {
  this.context=new AnnotationConfigWebApplicationContext();
  this.context.register(FacebookAutoConfiguration.class);
  this.context.register(SocialWebAutoConfiguration.class);
  this.context.refresh();
  assertNoConnectionFrameworkBeans();
  assertMissingBean(Facebook.class);
}
