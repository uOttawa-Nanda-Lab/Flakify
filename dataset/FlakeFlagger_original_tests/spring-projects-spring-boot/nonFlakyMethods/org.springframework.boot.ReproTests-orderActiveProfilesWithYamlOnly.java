@Test public void orderActiveProfilesWithYamlOnly() throws Exception {
  SpringApplication application=new SpringApplication(Config.class);
  application.setWebEnvironment(false);
  String configName="--spring.config.name=activeprofilerepro-ordered";
  assertVersionProperty(application.run(configName),"B","A","B");
}
