@Test public void disabledViaApplcationProperties() throws Exception {
  ConfigurableEnvironment environment=new StandardEnvironment();
  EnvironmentTestUtils.addEnvironment(environment,"spring.config.name:ansi");
  SpringApplication application=new SpringApplication(Config.class);
  application.setWebEnvironment(false);
  application.setEnvironment(environment);
  application.run();
  assertThat(AnsiOutput.getEnabled(),equalTo(Enabled.NEVER));
}
