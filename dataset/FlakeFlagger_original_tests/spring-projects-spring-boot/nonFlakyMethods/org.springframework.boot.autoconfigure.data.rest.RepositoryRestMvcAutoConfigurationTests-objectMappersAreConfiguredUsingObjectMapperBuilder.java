@Test public void objectMappersAreConfiguredUsingObjectMapperBuilder() throws JsonProcessingException {
  load(TestConfigurationWithObjectMapperBuilder.class);
  assertThatDateIsFormattedCorrectly("halObjectMapper");
  assertThatDateIsFormattedCorrectly("objectMapper");
}
