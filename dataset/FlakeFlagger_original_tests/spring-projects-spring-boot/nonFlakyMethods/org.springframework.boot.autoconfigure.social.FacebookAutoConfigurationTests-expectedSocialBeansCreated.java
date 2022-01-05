@Test public void expectedSocialBeansCreated() throws Exception {
  this.context=new AnnotationConfigWebApplicationContext();
  EnvironmentTestUtils.addEnvironment(this.context,"spring.social.facebook.appId:12345");
  EnvironmentTestUtils.addEnvironment(this.context,"spring.social.facebook.appSecret:secret");
  this.context.register(FacebookAutoConfiguration.class);
  this.context.register(SocialWebAutoConfiguration.class);
  this.context.refresh();
  assertConnectionFrameworkBeans();
  assertNotNull(this.context.getBean(Facebook.class));
}
