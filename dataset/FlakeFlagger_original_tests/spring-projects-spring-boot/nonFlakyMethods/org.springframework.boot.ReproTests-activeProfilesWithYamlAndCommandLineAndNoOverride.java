@Test public void activeProfilesWithYamlAndCommandLineAndNoOverride() throws Exception {
  SpringApplication application=new SpringApplication(Config.class);
  application.setWebEnvironment(false);
  String configName="--spring.config.name=activeprofilerepro-without-override";
  assertVersionProperty(application.run(configName,"--spring.profiles.active=B"),"B","B");
}
