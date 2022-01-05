@Test public void should_validate_id_for_entity() throws Exception {
  PropertyMeta idMeta=completeBean(Void.class,Long.class).field("id").type(ID).build();
  PropertyMeta nameMeta=completeBean(Void.class,String.class).field("name").type(SIMPLE).build();
  entityMeta.setIdMeta(idMeta);
  entityMeta.setPropertyMetas(Maps.<String,PropertyMeta>newHashMap());
  entityMeta.setAllMetasExceptIdAndCounters(Arrays.asList(nameMeta));
  when(tableMetaData.getName()).thenReturn("table");
  when(tableMetaData.getColumn("id")).thenReturn(columnMetadata);
  when(columnMetadata.getType()).thenReturn(DataType.bigint());
  when(tableMetaData.getColumn("name")).thenReturn(columnMetadataForField);
  when(columnMetadataForField.getType()).thenReturn(DataType.text());
  validator.validateForEntity(entityMeta,tableMetaData);
}
