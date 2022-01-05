@Test @SuppressWarnings("deprecation") public void customBannerWithProperties() throws Exception {
  SpringApplication application=spy(new SpringApplication(ExampleConfig.class));
  application.setWebEnvironment(false);
  application.run("--banner.location=classpath:test-banner-with-placeholder.txt","--test.property=123456");
  verify(application,never()).printBanner();
}
