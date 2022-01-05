@Test public void should_exception_when_no_counter_fqcn_column() throws Exception {
  tableMetaData=mock(TableMetadata.class);
  KeyspaceMetadata keyspaceMeta=mock(KeyspaceMetadata.class);
  when(cluster.getMetadata().getKeyspace(keyspaceName)).thenReturn(keyspaceMeta);
  when(keyspaceMeta.getTable(CQL_COUNTER_TABLE)).thenReturn(tableMetaData);
  exception.expect(AchillesInvalidTableException.class);
  exception.expectMessage(String.format("Cannot find column '%s' from table '%s'",CQL_COUNTER_FQCN,CQL_COUNTER_TABLE));
  validator.validateAchillesCounter(keyspaceMeta,keyspaceName);
}
