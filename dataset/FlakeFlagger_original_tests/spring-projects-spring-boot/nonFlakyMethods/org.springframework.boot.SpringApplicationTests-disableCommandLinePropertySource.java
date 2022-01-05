@Test public void disableCommandLinePropertySource() throws Exception {
  SpringApplication application=new SpringApplication(ExampleConfig.class);
  application.setWebEnvironment(false);
  application.setAddCommandLineProperties(false);
  ConfigurableEnvironment environment=new StandardEnvironment();
  application.setEnvironment(environment);
  application.run("--foo=bar");
  assertFalse(hasPropertySource(environment,PropertySource.class,"commandLineArgs"));
}
