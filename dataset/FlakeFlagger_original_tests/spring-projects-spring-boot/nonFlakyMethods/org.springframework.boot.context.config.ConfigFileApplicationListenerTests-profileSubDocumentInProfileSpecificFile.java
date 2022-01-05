@Test public void profileSubDocumentInProfileSpecificFile() throws Exception {
  SpringApplication application=new SpringApplication(Config.class);
  application.setWebEnvironment(false);
  ConfigurableApplicationContext context=application.run("--spring.profiles.active=activeprofilewithsubdoc");
  String property=context.getEnvironment().getProperty("foobar");
  assertThat(property,equalTo("baz"));
}
