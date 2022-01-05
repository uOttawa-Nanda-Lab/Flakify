@Test public void expectedSocialBeansCreated() throws Exception {
  this.context=new AnnotationConfigWebApplicationContext();
  EnvironmentTestUtils.addEnvironment(this.context,"spring.social.linkedin.appId:12345");
  EnvironmentTestUtils.addEnvironment(this.context,"spring.social.linkedin.appSecret:secret");
  this.context.register(LinkedInAutoConfiguration.class);
  this.context.register(SocialWebAutoConfiguration.class);
  this.context.refresh();
  assertConnectionFrameworkBeans();
  assertNotNull(this.context.getBean(LinkedIn.class));
}
