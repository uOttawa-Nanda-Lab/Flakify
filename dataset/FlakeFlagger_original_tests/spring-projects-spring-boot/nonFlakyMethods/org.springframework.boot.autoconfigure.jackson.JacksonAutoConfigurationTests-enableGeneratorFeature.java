@Test public void enableGeneratorFeature() throws Exception {
  this.context.register(JacksonAutoConfiguration.class);
  EnvironmentTestUtils.addEnvironment(this.context,"spring.jackson.generator.write_numbers_as_strings:true");
  this.context.refresh();
  ObjectMapper mapper=this.context.getBean(ObjectMapper.class);
  assertFalse(JsonGenerator.Feature.WRITE_NUMBERS_AS_STRINGS.enabledByDefault());
  assertTrue(mapper.getFactory().isEnabled(JsonGenerator.Feature.WRITE_NUMBERS_AS_STRINGS));
}
