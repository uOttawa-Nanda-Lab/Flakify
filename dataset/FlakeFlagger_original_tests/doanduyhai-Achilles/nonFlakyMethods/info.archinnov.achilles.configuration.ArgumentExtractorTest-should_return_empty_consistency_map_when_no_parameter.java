@Test public void should_return_empty_consistency_map_when_no_parameter() throws Exception {
  Map<String,ConsistencyLevel> consistencyMap=extractor.initWriteConsistencyMap(configMap);
  assertThat(consistencyMap).isEmpty();
}
