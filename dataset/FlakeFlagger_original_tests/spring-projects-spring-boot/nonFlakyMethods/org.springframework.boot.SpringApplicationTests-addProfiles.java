@Test public void addProfiles() throws Exception {
  SpringApplication application=new SpringApplication(ExampleConfig.class);
  application.setWebEnvironment(false);
  application.setAdditionalProfiles("foo");
  ConfigurableEnvironment environment=new StandardEnvironment();
  application.setEnvironment(environment);
  application.run();
  assertTrue(environment.acceptsProfiles("foo"));
}
