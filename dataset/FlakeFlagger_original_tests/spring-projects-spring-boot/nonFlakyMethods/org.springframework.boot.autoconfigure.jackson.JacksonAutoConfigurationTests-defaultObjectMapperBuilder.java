@Test public void defaultObjectMapperBuilder() throws Exception {
  this.context.register(JacksonAutoConfiguration.class);
  this.context.refresh();
  Jackson2ObjectMapperBuilder builder=this.context.getBean(Jackson2ObjectMapperBuilder.class);
  ObjectMapper mapper=builder.build();
  assertTrue(MapperFeature.DEFAULT_VIEW_INCLUSION.enabledByDefault());
  assertFalse(mapper.getDeserializationConfig().isEnabled(MapperFeature.DEFAULT_VIEW_INCLUSION));
  assertTrue(MapperFeature.DEFAULT_VIEW_INCLUSION.enabledByDefault());
  assertFalse(mapper.getDeserializationConfig().isEnabled(MapperFeature.DEFAULT_VIEW_INCLUSION));
  assertFalse(mapper.getSerializationConfig().isEnabled(MapperFeature.DEFAULT_VIEW_INCLUSION));
  assertTrue(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES.enabledByDefault());
  assertFalse(mapper.getDeserializationConfig().isEnabled(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES));
}
