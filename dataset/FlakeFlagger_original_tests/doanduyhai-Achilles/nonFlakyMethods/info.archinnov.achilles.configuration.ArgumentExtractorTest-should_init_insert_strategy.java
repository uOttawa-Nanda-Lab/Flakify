@Test public void should_init_insert_strategy() throws Exception {
  ConfigMap params=new ConfigMap();
  params.put(INSERT_STRATEGY,ALL_FIELDS);
  final InsertStrategy strategy=extractor.initInsertStrategy(params);
  assertThat(strategy).isEqualTo(ALL_FIELDS);
}
