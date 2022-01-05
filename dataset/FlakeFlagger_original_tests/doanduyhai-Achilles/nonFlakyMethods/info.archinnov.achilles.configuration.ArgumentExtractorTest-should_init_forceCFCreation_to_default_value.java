@Test public void should_init_forceCFCreation_to_default_value() throws Exception {
  boolean actual=extractor.initForceTableCreation(configMap);
  assertThat(actual).isFalse();
}
