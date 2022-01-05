@Test public void propertySourceAnnotationAndNonActiveProfile() throws Exception {
  SpringApplication application=new SpringApplication(WithPropertySourceAndProfile.class);
  application.setWebEnvironment(false);
  ConfigurableApplicationContext context=application.run();
  String property=context.getEnvironment().getProperty("my.property");
  assertThat(property,equalTo("fromapplicationproperties"));
  assertThat(context.getEnvironment(),not(containsPropertySource("classpath:" + "/enableprofile-myprofile.properties")));
  context.close();
}
