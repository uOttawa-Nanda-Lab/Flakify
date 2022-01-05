@Test public void should_validate_set_field_for_entity() throws Exception {
  PropertyMeta idMeta=completeBean(Void.class,Long.class).field("id").type(ID).build();
  PropertyMeta pm=completeBean(Void.class,String.class).field("followers").type(SET).build();
  entityMeta.setIdMeta(idMeta);
  entityMeta.setAllMetasExceptIdAndCounters(Arrays.asList(pm));
  entityMeta.setPropertyMetas(Maps.<String,PropertyMeta>newHashMap());
  when(tableMetaData.getName()).thenReturn("table");
  when(tableMetaData.getColumn("id")).thenReturn(columnMetadata);
  when(columnMetadata.getType()).thenReturn(DataType.bigint());
  when(tableMetaData.getColumn("followers")).thenReturn(columnMetadataForField);
  when(columnMetadataForField.getType()).thenReturn(DataType.set(DataType.text()));
  validator.validateForEntity(entityMeta,tableMetaData);
}
