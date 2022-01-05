@Test public void commandLinePropertySource() throws Exception {
  SpringApplication application=new SpringApplication(ExampleConfig.class);
  application.setWebEnvironment(false);
  ConfigurableEnvironment environment=new StandardEnvironment();
  application.setEnvironment(environment);
  application.run("--foo=bar");
  assertTrue(hasPropertySource(environment,CommandLinePropertySource.class,"commandLineArgs"));
}
