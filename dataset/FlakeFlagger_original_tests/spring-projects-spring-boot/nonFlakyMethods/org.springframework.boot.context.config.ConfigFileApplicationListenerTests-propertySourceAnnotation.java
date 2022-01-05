@Test public void propertySourceAnnotation() throws Exception {
  SpringApplication application=new SpringApplication(WithPropertySource.class);
  application.setWebEnvironment(false);
  ConfigurableApplicationContext context=application.run();
  String property=context.getEnvironment().getProperty("the.property");
  assertThat(property,equalTo("fromspecificlocation"));
  assertThat(context.getEnvironment(),containsPropertySource("class path resource " + "[specificlocation.properties]"));
  context.close();
}
