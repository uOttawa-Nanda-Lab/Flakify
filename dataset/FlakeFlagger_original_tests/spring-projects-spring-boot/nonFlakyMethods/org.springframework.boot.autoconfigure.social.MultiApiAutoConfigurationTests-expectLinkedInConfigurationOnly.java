@Test public void expectLinkedInConfigurationOnly() throws Exception {
  setupContext("spring.social.linkedin.appId:12345","spring.social.linkedin.appSecret:secret");
  assertConnectionFrameworkBeans();
  assertNotNull(this.context.getBean(LinkedIn.class));
  assertMissingBean(Twitter.class);
  assertMissingBean(Facebook.class);
}
