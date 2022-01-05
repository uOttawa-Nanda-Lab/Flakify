@Test public void activateProfileFromProfileSpecificProperties() throws Exception {
  SpringApplication application=new SpringApplication(Config.class);
  application.setWebEnvironment(false);
  ConfigurableApplicationContext context=application.run("--spring.profiles.active=includeprofile");
  assertThat(context.getEnvironment(),acceptsProfiles("includeprofile"));
  assertThat(context.getEnvironment(),acceptsProfiles("specific"));
  assertThat(context.getEnvironment(),acceptsProfiles("morespecific"));
  assertThat(context.getEnvironment(),acceptsProfiles("yetmorespecific"));
  assertThat(context.getEnvironment(),not(acceptsProfiles("missing")));
}
