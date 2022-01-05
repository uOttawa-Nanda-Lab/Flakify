@Test public void should_init_default_write_consistency_level() throws Exception {
  configMap.put(CONSISTENCY_LEVEL_WRITE_DEFAULT,LOCAL_QUORUM);
  assertThat(extractor.initDefaultWriteConsistencyLevel(configMap)).isEqualTo(LOCAL_QUORUM);
}
