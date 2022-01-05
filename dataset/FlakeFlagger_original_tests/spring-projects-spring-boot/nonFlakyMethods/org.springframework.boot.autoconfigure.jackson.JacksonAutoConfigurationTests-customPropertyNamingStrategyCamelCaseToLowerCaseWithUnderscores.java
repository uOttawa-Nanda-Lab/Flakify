@Test public void customPropertyNamingStrategyCamelCaseToLowerCaseWithUnderscores() throws Exception {
  this.context.register(JacksonAutoConfiguration.class);
  EnvironmentTestUtils.addEnvironment(this.context,"spring.jackson.property-naming-strategy:CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES");
  this.context.refresh();
  ObjectMapper mapper=this.context.getBean(ObjectMapper.class);
  assertEquals("{\"property_name\":null}",mapper.writeValueAsString(new Bar()));
}
