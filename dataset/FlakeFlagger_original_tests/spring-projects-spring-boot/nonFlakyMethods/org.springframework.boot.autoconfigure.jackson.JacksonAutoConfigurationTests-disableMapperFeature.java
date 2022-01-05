@Test public void disableMapperFeature() throws Exception {
  this.context.register(JacksonAutoConfiguration.class);
  EnvironmentTestUtils.addEnvironment(this.context,"spring.jackson.mapper.use_annotations:false");
  this.context.refresh();
  ObjectMapper mapper=this.context.getBean(ObjectMapper.class);
  assertTrue(MapperFeature.USE_ANNOTATIONS.enabledByDefault());
  assertFalse(mapper.getDeserializationConfig().hasMapperFeatures(MapperFeature.USE_ANNOTATIONS.getMask()));
  assertFalse(mapper.getSerializationConfig().hasMapperFeatures(MapperFeature.USE_ANNOTATIONS.getMask()));
}
