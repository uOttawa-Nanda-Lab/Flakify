@Test public void reverseOrderOfProfilesWithYaml() throws Exception {
  SpringApplication application=new SpringApplication(Config.class);
  application.setWebEnvironment(false);
  String configName="--spring.config.name=activeprofilerepro";
  assertVersionProperty(application.run(configName,"--spring.profiles.active=C,A"),"A","C","A");
}
