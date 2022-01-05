@Test public void disableSerializationFeature() throws Exception {
  this.context.register(JacksonAutoConfiguration.class);
  EnvironmentTestUtils.addEnvironment(this.context,"spring.jackson.serialization.write_dates_as_timestamps:false");
  this.context.refresh();
  ObjectMapper mapper=this.context.getBean(ObjectMapper.class);
  assertTrue(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS.enabledByDefault());
  assertFalse(mapper.getSerializationConfig().hasSerializationFeatures(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS.getMask()));
}
