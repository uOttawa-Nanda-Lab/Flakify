@Test public void enableMapperFeature() throws Exception {
  this.context.register(JacksonAutoConfiguration.class);
  EnvironmentTestUtils.addEnvironment(this.context,"spring.jackson.mapper.require_setters_for_getters:true");
  this.context.refresh();
  ObjectMapper mapper=this.context.getBean(ObjectMapper.class);
  assertFalse(MapperFeature.REQUIRE_SETTERS_FOR_GETTERS.enabledByDefault());
  assertTrue(mapper.getSerializationConfig().hasMapperFeatures(MapperFeature.REQUIRE_SETTERS_FOR_GETTERS.getMask()));
  assertTrue(mapper.getDeserializationConfig().hasMapperFeatures(MapperFeature.REQUIRE_SETTERS_FOR_GETTERS.getMask()));
}
