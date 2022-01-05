@Test public void disableGeneratorFeature() throws Exception {
  this.context.register(JacksonAutoConfiguration.class);
  EnvironmentTestUtils.addEnvironment(this.context,"spring.jackson.generator.auto_close_target:false");
  this.context.refresh();
  ObjectMapper mapper=this.context.getBean(ObjectMapper.class);
  assertTrue(JsonGenerator.Feature.AUTO_CLOSE_TARGET.enabledByDefault());
  assertFalse(mapper.getFactory().isEnabled(JsonGenerator.Feature.AUTO_CLOSE_TARGET));
}
