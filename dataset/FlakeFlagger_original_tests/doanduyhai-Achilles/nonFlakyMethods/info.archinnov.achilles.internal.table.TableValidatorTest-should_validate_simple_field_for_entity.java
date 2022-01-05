@Test public void should_validate_simple_field_for_entity() throws Exception {
  PropertyMeta idMeta=completeBean(Void.class,Long.class).field("id").type(ID).build();
  PropertyMeta pm=completeBean(Void.class,String.class).field("name").type(SIMPLE).build();
  entityMeta.setIdMeta(idMeta);
  entityMeta.setAllMetasExceptIdAndCounters(Arrays.asList(pm));
  entityMeta.setPropertyMetas(Maps.<String,PropertyMeta>newHashMap());
  when(tableMetaData.getName()).thenReturn("table");
  when(tableMetaData.getColumn("id")).thenReturn(columnMetadata);
  when(columnMetadata.getType()).thenReturn(DataType.bigint());
  when(columnMetadata.getIndex()).thenReturn(null);
  when(tableMetaData.getColumn("name")).thenReturn(columnMetadataForField);
  when(columnMetadataForField.getType()).thenReturn(DataType.text());
  when(columnMetadataForField.getIndex()).thenReturn(null);
  validator.validateForEntity(entityMeta,tableMetaData);
  pm=completeBean(Void.class,String.class).field("name").type(SIMPLE).build();
  entityMeta.setPropertyMetas(ImmutableMap.of("name",pm));
  validator.validateForEntity(entityMeta,tableMetaData);
}
