@Test public void propertySourceAnnotationMultipleLocations() throws Exception {
  SpringApplication application=new SpringApplication(WithPropertySourceMultipleLocations.class);
  application.setWebEnvironment(false);
  ConfigurableApplicationContext context=application.run();
  String property=context.getEnvironment().getProperty("the.property");
  assertThat(property,equalTo("frommorepropertiesfile"));
  assertThat(context.getEnvironment(),containsPropertySource("class path resource " + "[specificlocation.properties]"));
  context.close();
}
