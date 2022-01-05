@Test public void disableBanner() throws Exception {
  SpringApplication application=spy(new SpringApplication(ExampleConfig.class));
  application.setWebEnvironment(false);
  application.setShowBanner(false);
  application.run();
  verify(application,never()).printBanner((Environment)anyObject());
}
