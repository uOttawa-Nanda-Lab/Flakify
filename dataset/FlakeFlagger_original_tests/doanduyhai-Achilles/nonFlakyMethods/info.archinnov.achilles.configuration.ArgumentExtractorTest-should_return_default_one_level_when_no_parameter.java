@Test public void should_return_default_one_level_when_no_parameter() throws Exception {
  assertThat(extractor.initDefaultReadConsistencyLevel(configMap)).isEqualTo(ONE);
}
