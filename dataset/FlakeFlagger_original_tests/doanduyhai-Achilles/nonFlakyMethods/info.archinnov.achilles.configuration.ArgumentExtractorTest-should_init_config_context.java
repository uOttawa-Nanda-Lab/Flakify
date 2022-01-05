@Test public void should_init_config_context() throws Exception {
  ConfigMap params=new ConfigMap();
  doReturn(true).when(extractor).initForceTableCreation(params);
  doReturn(factory).when(extractor).initObjectMapperFactory(params);
  doReturn(ANY).when(extractor).initDefaultReadConsistencyLevel(params);
  doReturn(ALL).when(extractor).initDefaultWriteConsistencyLevel(params);
  doReturn(ImmutableMap.of("table1",EACH_QUORUM)).when(extractor).initReadConsistencyMap(params);
  doReturn(ImmutableMap.of("table2",ConsistencyLevel.LOCAL_QUORUM)).when(extractor).initWriteConsistencyMap(params);
  doReturn(ALL).when(extractor).initDefaultWriteConsistencyLevel(params);
  ConfigurationContext configContext=extractor.initConfigContext(params);
  assertThat(configContext.isForceColumnFamilyCreation()).isTrue();
  assertThat(configContext.getObjectMapperFactory()).isSameAs(factory);
  assertThat(configContext.getDefaultReadConsistencyLevel()).isEqualTo(ANY);
  assertThat(configContext.getDefaultWriteConsistencyLevel()).isEqualTo(ALL);
  assertThat(configContext.getDefaultWriteConsistencyLevel()).isEqualTo(ALL);
  assertThat(configContext.getBeanValidator()).isNull();
  assertThat(configContext.getPreparedStatementLRUCacheSize()).isEqualTo(DEFAULT_LRU_CACHE_SIZE);
  assertThat(configContext.getInsertStrategy()).isEqualTo(ALL_FIELDS);
  assertThat(configContext.getInsertStrategy()).isEqualTo(ALL_FIELDS);
}
