@Test public void enableSerializationFeature() throws Exception {
  this.context.register(JacksonAutoConfiguration.class);
  EnvironmentTestUtils.addEnvironment(this.context,"spring.jackson.serialization.indent_output:true");
  this.context.refresh();
  ObjectMapper mapper=this.context.getBean(ObjectMapper.class);
  assertFalse(SerializationFeature.INDENT_OUTPUT.enabledByDefault());
  assertTrue(mapper.getSerializationConfig().hasSerializationFeatures(SerializationFeature.INDENT_OUTPUT.getMask()));
}
