@Test public void should_fetch_table_metas() throws Exception {
  Map<String,TableMetadata> expected=new HashMap<String,TableMetadata>();
  KeyspaceMetadata keyspaceMeta=mock(KeyspaceMetadata.class);
  when(cluster.getMetadata().getKeyspace(keyspaceName)).thenReturn(keyspaceMeta);
  when(tableCreator.fetchTableMetaData(keyspaceMeta,keyspaceName)).thenReturn(expected);
  Map<String,TableMetadata> actual=context.fetchTableMetaData();
  assertThat(actual).isSameAs(expected);
}
