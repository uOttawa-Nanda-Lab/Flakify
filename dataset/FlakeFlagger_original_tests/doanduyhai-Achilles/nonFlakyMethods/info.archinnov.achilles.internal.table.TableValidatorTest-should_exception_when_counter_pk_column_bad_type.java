@Test public void should_exception_when_counter_pk_column_bad_type(){
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
  when(columnMetaDataComparator.isEqual(fqcnColumnMeta,fqcnColumn)).thenReturn(true);
  ColumnMetadata pkColumnMeta=mock(ColumnMetadata.class);
  when(tableMetaData.getColumn(CQL_COUNTER_PRIMARY_KEY)).thenReturn(pkColumnMeta);
  when(pkColumnMeta.getType()).thenReturn(DataType.blob());
  exception.expect(AchillesInvalidTableException.class);
  exception.expectMessage(String.format("Column '%s' of type '%s' should be of type '%s'",CQL_COUNTER_PRIMARY_KEY,pkColumnMeta.getType(),text()));
  validator.validateAchillesCounter(keyspaceMeta,keyspaceName);
}
