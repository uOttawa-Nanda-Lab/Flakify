@Test public void expectLinkedInAndTwitterConfigurationOnly() throws Exception {
  setupContext("spring.social.linkedin.appId:54321","spring.social.linkedin.appSecret:shhhhh","spring.social.twitter.appId:12345","spring.social.twitter.appSecret:secret");
  assertConnectionFrameworkBeans();
  assertNotNull(this.context.getBean(LinkedIn.class));
  assertNotNull(this.context.getBean(Twitter.class));
  assertMissingBean(Facebook.class);
}
