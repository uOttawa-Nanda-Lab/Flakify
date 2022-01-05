@Test public void disableParserFeature() throws Exception {
  this.context.register(JacksonAutoConfiguration.class);
  EnvironmentTestUtils.addEnvironment(this.context,"spring.jackson.parser.auto_close_source:false");
  this.context.refresh();
  ObjectMapper mapper=this.context.getBean(ObjectMapper.class);
  assertTrue(JsonParser.Feature.AUTO_CLOSE_SOURCE.enabledByDefault());
  assertFalse(mapper.getFactory().isEnabled(JsonParser.Feature.AUTO_CLOSE_SOURCE));
}
