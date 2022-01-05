@Test public void should_return_empty_consistency_map_when_empty_map_parameter() throws Exception {
  configMap.put(CONSISTENCY_LEVEL_WRITE_MAP,new HashMap<String,String>());
  Map<String,ConsistencyLevel> consistencyMap=extractor.initWriteConsistencyMap(configMap);
  assertThat(consistencyMap).isEmpty();
}
