@Test public void activeProfilesWithYamlOnlyAndNoOverride() throws Exception {
  SpringApplication application=new SpringApplication(Config.class);
  application.setWebEnvironment(false);
  String configName="--spring.config.name=activeprofilerepro-without-override";
  assertVersionProperty(application.run(configName),null);
}
