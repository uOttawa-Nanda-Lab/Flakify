@Test public void should_init_read_consistency_level_map() throws Exception {
  configMap.put(CONSISTENCY_LEVEL_READ_MAP,ImmutableMap.of("cf1",ONE,"cf2",LOCAL_QUORUM));
  Map<String,ConsistencyLevel> consistencyMap=extractor.initReadConsistencyMap(configMap);
  assertThat(consistencyMap.get("cf1")).isEqualTo(ConsistencyLevel.ONE);
  assertThat(consistencyMap.get("cf2")).isEqualTo(ConsistencyLevel.LOCAL_QUORUM);
}
