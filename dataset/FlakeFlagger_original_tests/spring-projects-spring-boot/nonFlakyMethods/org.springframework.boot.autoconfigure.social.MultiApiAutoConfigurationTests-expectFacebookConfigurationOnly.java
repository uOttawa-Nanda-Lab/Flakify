@Test public void expectFacebookConfigurationOnly() throws Exception {
  setupContext("spring.social.facebook.appId:12345","spring.social.facebook.appSecret:secret");
  assertConnectionFrameworkBeans();
  assertNotNull(this.context.getBean(Facebook.class));
  assertMissingBean(Twitter.class);
  assertMissingBean(LinkedIn.class);
}
