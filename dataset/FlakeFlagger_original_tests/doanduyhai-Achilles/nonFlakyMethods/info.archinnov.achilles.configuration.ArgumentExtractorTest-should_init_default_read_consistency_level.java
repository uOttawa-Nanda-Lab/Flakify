@Test public void should_init_default_read_consistency_level() throws Exception {
  configMap.put(CONSISTENCY_LEVEL_READ_DEFAULT,ONE);
  assertThat(extractor.initDefaultReadConsistencyLevel(configMap)).isEqualTo(ONE);
}
