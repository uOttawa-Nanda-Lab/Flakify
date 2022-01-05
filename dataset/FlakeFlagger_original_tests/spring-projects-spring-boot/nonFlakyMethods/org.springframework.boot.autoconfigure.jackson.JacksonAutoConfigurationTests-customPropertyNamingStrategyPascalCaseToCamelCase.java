@Test public void customPropertyNamingStrategyPascalCaseToCamelCase() throws Exception {
  this.context.register(JacksonAutoConfiguration.class);
  EnvironmentTestUtils.addEnvironment(this.context,"spring.jackson.property-naming-strategy:PASCAL_CASE_TO_CAMEL_CASE");
  this.context.refresh();
  ObjectMapper mapper=this.context.getBean(ObjectMapper.class);
  assertEquals("{\"PropertyName\":null}",mapper.writeValueAsString(new Bar()));
}
