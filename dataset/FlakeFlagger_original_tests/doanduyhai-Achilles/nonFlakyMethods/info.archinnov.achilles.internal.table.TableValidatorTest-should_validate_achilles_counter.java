@Test public void should_validate_achilles_counter() throws Exception {
  KeyspaceMetadata keyspaceMeta=mock(KeyspaceMetadata.class);
  when(cluster.getMetadata().getKeyspace(keyspaceName)).thenReturn(keyspaceMeta);
  when(keyspaceMeta.getTable(CQL_COUNTER_TABLE)).thenReturn(tableMetaData);
  ColumnMetadata fqcnColumn=mock(ColumnMetadata.class);
  ColumnMetadata pkColumn=mock(ColumnMetadata.class);
  ColumnMetadata propertyColumn=mock(ColumnMetadata.class);
  when(tableMetaData.getPartitionKey()).thenReturn(Arrays.asList(fqcnColumn,pkColumn));
  ColumnMetadata fqcnColumnMeta=mock(ColumnMetadata.class);
  when(tableMetaData.getColumn(CQL_COUNTER_FQCN)).thenReturn(fqcnColumnMeta);
  when(fqcnColumnMeta.getType()).thenReturn(DataType.text());
  when(columnMetaDataComparator.isEqual(fqcnColumnMeta,fqcnColumn)).thenReturn(true);
  ColumnMetadata pkColumnMeta=mock(ColumnMetadata.class);
  when(tableMetaData.getColumn(CQL_COUNTER_PRIMARY_KEY)).thenReturn(pkColumnMeta);
  when(pkColumnMeta.getType()).thenReturn(DataType.text());
  when(columnMetaDataComparator.isEqual(pkColumnMeta,pkColumn)).thenReturn(true);
  ColumnMetadata propertyColumnMeta=mock(ColumnMetadata.class);
  when(tableMetaData.getColumn(CQL_COUNTER_PROPERTY_NAME)).thenReturn(propertyColumnMeta);
  when(propertyColumnMeta.getType()).thenReturn(DataType.text());
  when(tableMetaData.getClusteringColumns()).thenReturn(Arrays.asList(propertyColumn));
  when(columnMetaDataComparator.isEqual(propertyColumnMeta,propertyColumn)).thenReturn(true);
  ColumnMetadata counterColumnMeta=mock(ColumnMetadata.class);
  when(tableMetaData.getColumn(CQL_COUNTER_VALUE)).thenReturn(counterColumnMeta);
  when(counterColumnMeta.getType()).thenReturn(DataType.counter());
  validator.validateAchillesCounter(keyspaceMeta,keyspaceName);
}
