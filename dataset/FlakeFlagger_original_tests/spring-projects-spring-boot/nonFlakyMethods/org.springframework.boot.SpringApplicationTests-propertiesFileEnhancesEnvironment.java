@Test public void propertiesFileEnhancesEnvironment() throws Exception {
  SpringApplication application=new SpringApplication(ExampleConfig.class);
  application.setWebEnvironment(false);
  ConfigurableEnvironment environment=new StandardEnvironment();
  application.setEnvironment(environment);
  application.run();
  assertEquals("bucket",environment.getProperty("foo"));
}
