@Test @SuppressWarnings("deprecation") public void customBanner() throws Exception {
  SpringApplication application=spy(new SpringApplication(ExampleConfig.class));
  application.setWebEnvironment(false);
  application.run("--banner.location=classpath:test-banner.txt");
  verify(application,never()).printBanner();
}
