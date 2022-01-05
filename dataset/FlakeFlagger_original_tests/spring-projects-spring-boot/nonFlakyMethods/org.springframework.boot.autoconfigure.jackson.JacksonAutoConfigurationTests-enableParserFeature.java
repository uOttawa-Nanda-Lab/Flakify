@Test public void enableParserFeature() throws Exception {
  this.context.register(JacksonAutoConfiguration.class);
  EnvironmentTestUtils.addEnvironment(this.context,"spring.jackson.parser.allow_single_quotes:true");
  this.context.refresh();
  ObjectMapper mapper=this.context.getBean(ObjectMapper.class);
  assertFalse(JsonParser.Feature.ALLOW_SINGLE_QUOTES.enabledByDefault());
  assertTrue(mapper.getFactory().isEnabled(JsonParser.Feature.ALLOW_SINGLE_QUOTES));
}
