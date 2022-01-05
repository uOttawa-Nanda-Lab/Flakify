@Test public void commandLinePropertySourceEnhancesEnvironment() throws Exception {
  SpringApplication application=new SpringApplication(ExampleConfig.class);
  application.setWebEnvironment(false);
  ConfigurableEnvironment environment=new StandardEnvironment();
  environment.getPropertySources().addFirst(new MapPropertySource("commandLineArgs",Collections.<String,Object>singletonMap("foo","original")));
  application.setEnvironment(environment);
  application.run("--foo=bar","--bar=foo");
  assertTrue(hasPropertySource(environment,CompositePropertySource.class,"commandLineArgs"));
  assertEquals("foo",environment.getProperty("bar"));
  assertEquals("bar",environment.getProperty("foo"));
}
