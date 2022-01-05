@Test public void enableDeserializationFeature() throws Exception {
  this.context.register(JacksonAutoConfiguration.class);
  EnvironmentTestUtils.addEnvironment(this.context,"spring.jackson.deserialization.use_big_decimal_for_floats:true");
  this.context.refresh();
  ObjectMapper mapper=this.context.getBean(ObjectMapper.class);
  assertFalse(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS.enabledByDefault());
  assertTrue(mapper.getDeserializationConfig().hasDeserializationFeatures(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS.getMask()));
}
