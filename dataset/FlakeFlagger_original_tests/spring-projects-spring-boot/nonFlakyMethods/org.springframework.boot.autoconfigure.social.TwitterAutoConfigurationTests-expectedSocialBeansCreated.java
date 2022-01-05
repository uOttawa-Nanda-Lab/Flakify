@Test public void expectedSocialBeansCreated() throws Exception {
  this.context=new AnnotationConfigWebApplicationContext();
  EnvironmentTestUtils.addEnvironment(this.context,"spring.social.twitter.appId:12345");
  EnvironmentTestUtils.addEnvironment(this.context,"spring.social.twitter.appSecret:secret");
  this.context.register(TwitterAutoConfiguration.class);
  this.context.register(SocialWebAutoConfiguration.class);
  this.context.refresh();
  assertConnectionFrameworkBeans();
  assertNotNull(this.context.getBean(Twitter.class));
}
