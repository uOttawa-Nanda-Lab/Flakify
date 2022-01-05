@Test public void addProfilesOrderWithProperties() throws Exception {
  SpringApplication application=new SpringApplication(ExampleConfig.class);
  application.setWebEnvironment(false);
  application.setAdditionalProfiles("other");
  ConfigurableEnvironment environment=new StandardEnvironment();
  application.setEnvironment(environment);
  application.run();
  assertEquals("fromotherpropertiesfile",environment.getProperty("my.property"));
}
