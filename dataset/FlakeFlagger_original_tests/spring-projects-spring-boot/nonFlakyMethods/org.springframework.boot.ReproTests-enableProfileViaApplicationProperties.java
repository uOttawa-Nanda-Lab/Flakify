@Test public void enableProfileViaApplicationProperties() throws Exception {
  SpringApplication application=new SpringApplication(Config.class);
  application.setWebEnvironment(false);
  ConfigurableApplicationContext context=application.run("--spring.config.name=enableprofileviaapplicationproperties","--spring.profiles.active=dev");
  assertThat(context.getEnvironment().acceptsProfiles("dev"),equalTo(true));
  assertThat(context.getEnvironment().acceptsProfiles("a"),equalTo(true));
}
