@Test public void expectTwitterConfigurationOnly() throws Exception {
  setupContext("spring.social.twitter.appId:12345","spring.social.twitter.appSecret:secret");
  assertConnectionFrameworkBeans();
  assertNotNull(this.context.getBean(Twitter.class));
  assertMissingBean(Facebook.class);
  assertMissingBean(LinkedIn.class);
}
