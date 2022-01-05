@Test public void propertySourceAnnotationMultipleLocationsAndName() throws Exception {
  SpringApplication application=new SpringApplication(WithPropertySourceMultipleLocationsAndName.class);
  application.setWebEnvironment(false);
  ConfigurableApplicationContext context=application.run();
  String property=context.getEnvironment().getProperty("the.property");
  assertThat(property,equalTo("frommorepropertiesfile"));
  assertThat(context.getEnvironment(),containsPropertySource("foo"));
  context.close();
}
