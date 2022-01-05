@Test public void activeProfilesWithYamlOnly() throws Exception {
  SpringApplication application=new SpringApplication(Config.class);
  application.setWebEnvironment(false);
  String configName="--spring.config.name=activeprofilerepro";
  assertVersionProperty(application.run(configName),"B","B");
}
