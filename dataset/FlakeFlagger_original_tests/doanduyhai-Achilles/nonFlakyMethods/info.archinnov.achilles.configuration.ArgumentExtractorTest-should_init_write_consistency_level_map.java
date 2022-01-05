@Test public void should_init_write_consistency_level_map() throws Exception {
  configMap.put(CONSISTENCY_LEVEL_WRITE_MAP,ImmutableMap.of("cf1",THREE,"cf2",EACH_QUORUM));
  Map<String,ConsistencyLevel> consistencyMap=extractor.initWriteConsistencyMap(configMap);
  assertThat(consistencyMap.get("cf1")).isEqualTo(THREE);
  assertThat(consistencyMap.get("cf2")).isEqualTo(EACH_QUORUM);
}
