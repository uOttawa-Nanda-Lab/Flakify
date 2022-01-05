@Test public void should_init_force_update_map() throws Exception {
  configMap.put(ENABLE_SCHEMA_UPDATE_FOR_TABLES,ImmutableMap.of("myTable",true));
  Map<String,Boolean> tables=extractor.initForceTableUpdateMap(configMap);
  assertThat(tables).contains(MapEntry.entry("myTable",true));
}
