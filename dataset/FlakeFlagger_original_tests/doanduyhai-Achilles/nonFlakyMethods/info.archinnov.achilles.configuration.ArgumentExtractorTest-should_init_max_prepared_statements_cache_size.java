@Test public void should_init_max_prepared_statements_cache_size() throws Exception {
  ConfigMap params=new ConfigMap();
  params.put(PREPARED_STATEMENTS_CACHE_SIZE,10);
  Integer actual=extractor.initPreparedStatementsCacheSize(params);
  assertThat(actual).isEqualTo(10);
}
