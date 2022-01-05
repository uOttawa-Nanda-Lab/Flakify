@Test public void expectFacebookAndLinkedInConfigurationOnly() throws Exception {
  setupContext("spring.social.facebook.appId:54321","spring.social.facebook.appSecret:shhhhh","spring.social.linkedin.appId:12345","spring.social.linkedin.appSecret:secret");
  assertConnectionFrameworkBeans();
  assertNotNull(this.context.getBean(Facebook.class));
  assertNotNull(this.context.getBean(LinkedIn.class));
  assertMissingBean(Twitter.class);
}
