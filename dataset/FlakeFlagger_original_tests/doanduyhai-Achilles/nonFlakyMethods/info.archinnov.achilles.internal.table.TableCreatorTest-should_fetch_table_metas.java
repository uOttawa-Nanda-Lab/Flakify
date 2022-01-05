@Test public void should_fetch_table_metas() throws Exception {
  List<TableMetadata> tableMetas=asList(tableMeta);
  when(keyspaceMeta.getTables()).thenReturn(tableMetas);
  when(tableMeta.getName()).thenReturn("table");
  Map<String,TableMetadata> actual=creator.fetchTableMetaData(keyspaceMeta,"keyspace");
  assertThat(actual.get("table")).isSameAs(tableMeta);
}
