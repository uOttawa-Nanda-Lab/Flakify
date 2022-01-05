@Test public void expectFacebookAndTwitterConfigurationOnly() throws Exception {
  setupContext("spring.social.facebook.appId:54321","spring.social.facebook.appSecret:shhhhh","spring.social.twitter.appId:12345","spring.social.twitter.appSecret:secret");
  assertConnectionFrameworkBeans();
  assertNotNull(this.context.getBean(Facebook.class));
  assertNotNull(this.context.getBean(Twitter.class));
  assertMissingBean(LinkedIn.class);
}
