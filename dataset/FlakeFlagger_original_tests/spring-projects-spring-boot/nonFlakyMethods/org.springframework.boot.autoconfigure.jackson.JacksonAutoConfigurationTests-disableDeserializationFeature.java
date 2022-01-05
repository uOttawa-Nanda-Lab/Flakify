@Test public void disableDeserializationFeature() throws Exception {
  this.context.register(JacksonAutoConfiguration.class);
  EnvironmentTestUtils.addEnvironment(this.context,"spring.jackson.deserialization.fail_on_unknown_properties:false");
  this.context.refresh();
  ObjectMapper mapper=this.context.getBean(ObjectMapper.class);
  assertTrue(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES.enabledByDefault());
  assertFalse(mapper.getDeserializationConfig().hasDeserializationFeatures(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES.getMask()));
}
