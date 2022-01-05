@Test public void noSocialBeansCreatedWhenPropertiesArentSet() throws Exception {
  setupContext();
  assertNoConnectionFrameworkBeans();
  assertMissingBean(Twitter.class);
  assertMissingBean(Facebook.class);
  assertMissingBean(LinkedIn.class);
}
