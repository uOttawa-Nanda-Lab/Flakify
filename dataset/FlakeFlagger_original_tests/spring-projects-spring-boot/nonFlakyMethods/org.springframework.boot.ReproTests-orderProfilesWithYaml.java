@Test public void orderProfilesWithYaml() throws Exception {
  SpringApplication application=new SpringApplication(Config.class);
  application.setWebEnvironment(false);
  String configName="--spring.config.name=activeprofilerepro";
  assertVersionProperty(application.run(configName,"--spring.profiles.active=A,C"),"C","A","C");
}
