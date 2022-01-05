@Test public void should_validate_embedded_id_with_time_uuid_for_entity() throws Exception {
  PropertyMeta idMeta=valueClass(EmbeddedKey.class).compNames("userId","date").compClasses(Long.class,UUID.class).type(EMBEDDED_ID).compTimeUUID("date").build();
  PropertyMeta nameMeta=completeBean(Void.class,String.class).field("name").type(SIMPLE).build();
  entityMeta.setIdMeta(idMeta);
  entityMeta.setAllMetasExceptIdAndCounters(Arrays.asList(nameMeta));
  entityMeta.setPropertyMetas(Maps.<String,PropertyMeta>newHashMap());
  ColumnMetadata userColumn=mock(ColumnMetadata.class);
  ColumnMetadata nameColumn=mock(ColumnMetadata.class);
  when(tableMetaData.getName()).thenReturn("table");
  ColumnMetadata userIdMetadata=mock(ColumnMetadata.class);
  when(tableMetaData.getColumn("userid")).thenReturn(userIdMetadata);
  when(userIdMetadata.getType()).thenReturn(DataType.bigint());
  when(tableMetaData.getPartitionKey()).thenReturn(Arrays.asList(userColumn));
  when(columnMetaDataComparator.isEqual(userIdMetadata,userColumn)).thenReturn(true);
  ColumnMetadata nameMetadata=mock(ColumnMetadata.class);
  when(tableMetaData.getColumn("date")).thenReturn(nameMetadata);
  when(nameMetadata.getType()).thenReturn(DataType.timeuuid());
  when(tableMetaData.getClusteringColumns()).thenReturn(Arrays.asList(nameColumn));
  when(columnMetaDataComparator.isEqual(nameMetadata,nameColumn)).thenReturn(true);
  when(tableMetaData.getColumn("name")).thenReturn(columnMetadataForField);
  when(columnMetadataForField.getType()).thenReturn(DataType.text());
  validator.validateForEntity(entityMeta,tableMetaData);
}
