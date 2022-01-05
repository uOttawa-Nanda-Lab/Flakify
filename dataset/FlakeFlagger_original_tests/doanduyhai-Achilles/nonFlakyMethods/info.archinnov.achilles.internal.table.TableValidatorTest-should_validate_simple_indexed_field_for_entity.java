@Test public void should_validate_simple_indexed_field_for_entity() throws Exception {
  PropertyMeta idMeta=completeBean(Void.class,Long.class).field("id").type(ID).build();
  PropertyMeta pm=completeBean(Void.class,String.class).field("name").type(SIMPLE).build();
  pm.setIndexProperties(new IndexProperties("",""));
  entityMeta.setIdMeta(idMeta);
  entityMeta.setAllMetasExceptIdAndCounters(Arrays.asList(pm));
  entityMeta.setPropertyMetas(Maps.<String,PropertyMeta>newHashMap());
  when(tableMetaData.getName()).thenReturn("table");
  when(tableMetaData.getColumn("id")).thenReturn(columnMetadata);
  when(columnMetadata.getType()).thenReturn(DataType.bigint());
  when(columnMetadata.getIndex()).thenReturn(null);
  when(tableMetaData.getColumn("name")).thenReturn(columnMetadataForField);
  when(columnMetadataForField.getType()).thenReturn(DataType.text());
  IndexMetadata indexMetadata=mock(IndexMetadata.class);
  when(indexMetadata.getName()).thenReturn("table(name)");
  when(indexMetadata.getIndexedColumn()).thenReturn(columnMetadataForField);
  when(columnMetadataForField.getIndex()).thenReturn(indexMetadata);
  validator.validateForEntity(entityMeta,tableMetaData);
}
