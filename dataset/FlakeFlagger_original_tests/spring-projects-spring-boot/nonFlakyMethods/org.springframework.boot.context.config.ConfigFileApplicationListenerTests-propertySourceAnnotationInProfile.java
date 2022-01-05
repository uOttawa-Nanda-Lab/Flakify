@Test public void propertySourceAnnotationInProfile() throws Exception {
  SpringApplication application=new SpringApplication(WithPropertySourceInProfile.class);
  application.setWebEnvironment(false);
  ConfigurableApplicationContext context=application.run("--spring.profiles.active=myprofile");
  String property=context.getEnvironment().getProperty("the.property");
  assertThat(property,equalTo("frompropertiesfile"));
  assertThat(context.getEnvironment(),containsPropertySource("class path resource " + "[enableprofile.properties]"));
  assertThat(context.getEnvironment(),not(containsPropertySource("classpath:/" + "enableprofile-myprofile.properties")));
  context.close();
}
