@Test public void propertySourceAnnotationWithName() throws Exception {
  SpringApplication application=new SpringApplication(WithPropertySourceAndName.class);
  application.setWebEnvironment(false);
  ConfigurableApplicationContext context=application.run();
  String property=context.getEnvironment().getProperty("the.property");
  assertThat(property,equalTo("fromspecificlocation"));
  assertThat(context.getEnvironment(),containsPropertySource("foo"));
  context.close();
}
