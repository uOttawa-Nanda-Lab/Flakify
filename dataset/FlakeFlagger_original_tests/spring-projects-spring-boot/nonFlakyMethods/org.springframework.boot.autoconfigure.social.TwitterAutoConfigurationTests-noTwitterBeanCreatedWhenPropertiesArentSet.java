@Test public void noTwitterBeanCreatedWhenPropertiesArentSet() throws Exception {
  this.context=new AnnotationConfigWebApplicationContext();
  this.context.register(TwitterAutoConfiguration.class);
  this.context.register(SocialWebAutoConfiguration.class);
  this.context.refresh();
  assertNoConnectionFrameworkBeans();
  assertMissingBean(Twitter.class);
}
