@Test public void addProfilesOrder() throws Exception {
  SpringApplication application=new SpringApplication(ExampleConfig.class);
  application.setWebEnvironment(false);
  application.setAdditionalProfiles("foo");
  ConfigurableEnvironment environment=new StandardEnvironment();
  application.setEnvironment(environment);
  application.run("--spring.profiles.active=bar,spam");
  assertArrayEquals(new String[]{"foo","bar","spam"},environment.getActiveProfiles());
}
