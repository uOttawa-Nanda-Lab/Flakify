@Test public void propertySourceAnnotationWithPlaceholder() throws Exception {
  EnvironmentTestUtils.addEnvironment(this.environment,"source.location:specificlocation");
  SpringApplication application=new SpringApplication(WithPropertySourcePlaceholders.class);
  application.setEnvironment(this.environment);
  application.setWebEnvironment(false);
  ConfigurableApplicationContext context=application.run();
  String property=context.getEnvironment().getProperty("the.property");
  assertThat(property,equalTo("fromspecificlocation"));
  assertThat(context.getEnvironment(),containsPropertySource("class path resource " + "[specificlocation.properties]"));
  context.close();
}
