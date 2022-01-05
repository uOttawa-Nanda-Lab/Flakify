@Test public void should_exception_when_not_matching_counter_fqcn_column(){
  tableMetaData=mock(TableMetadata.class);
  KeyspaceMetadata keyspaceMeta=mock(KeyspaceMetadata.class);
  when(cluster.getMetadata().getKeyspace(keyspaceName)).thenReturn(keyspaceMeta);
  when(keyspaceMeta.getTable(CQL_COUNTER_TABLE)).thenReturn(tableMetaData);
  ColumnMetadata fqcnColumn=mock(ColumnMetadata.class);
  ColumnMetadata pkColumn=mock(ColumnMetadata.class);
  when(tableMetaData.getPartitionKey()).thenReturn(Arrays.asList(fqcnColumn,pkColumn));
  ColumnMetadata fqcnColumnMeta=mock(ColumnMetadata.class);
  when(tableMetaData.getColumn(CQL_COUNTER_FQCN)).thenReturn(fqcnColumnMeta);
  when(fqcnColumnMeta.getType()).thenReturn(DataType.text());
  exception.expect(AchillesBeanMappingException.class);
  exception.expectMessage(String.format("Column '%s' of table '%s' should be a partition key component",CQL_COUNTER_FQCN,CQL_COUNTER_TABLE));
  validator.validateAchillesCounter(keyspaceMeta,keyspaceName);
}
