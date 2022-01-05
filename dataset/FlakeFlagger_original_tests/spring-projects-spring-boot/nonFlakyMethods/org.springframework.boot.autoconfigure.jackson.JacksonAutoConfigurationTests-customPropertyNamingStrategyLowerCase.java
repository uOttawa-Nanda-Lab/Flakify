@Test public void customPropertyNamingStrategyLowerCase() throws Exception {
  this.context.register(JacksonAutoConfiguration.class);
  EnvironmentTestUtils.addEnvironment(this.context,"spring.jackson.property-naming-strategy:LOWER_CASE");
  this.context.refresh();
  ObjectMapper mapper=this.context.getBean(ObjectMapper.class);
  assertEquals("{\"propertyname\":null}",mapper.writeValueAsString(new Bar()));
}
