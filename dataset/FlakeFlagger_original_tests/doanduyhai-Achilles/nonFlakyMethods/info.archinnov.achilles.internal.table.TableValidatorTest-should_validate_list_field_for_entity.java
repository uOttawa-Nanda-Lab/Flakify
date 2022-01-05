@Test public void should_validate_list_field_for_entity() throws Exception {
  PropertyMeta idMeta=completeBean(Void.class,Long.class).field("id").type(ID).build();
  PropertyMeta pm=completeBean(Void.class,String.class).field("friends").type(LIST).build();
  entityMeta.setIdMeta(idMeta);
  entityMeta.setAllMetasExceptIdAndCounters(Arrays.asList(pm));
  entityMeta.setPropertyMetas(Maps.<String,PropertyMeta>newHashMap());
  when(tableMetaData.getName()).thenReturn("table");
  when(tableMetaData.getColumn("id")).thenReturn(columnMetadata);
  when(columnMetadata.getType()).thenReturn(DataType.bigint());
  when(tableMetaData.getColumn("friends")).thenReturn(columnMetadataForField);
  when(columnMetadataForField.getType()).thenReturn(DataType.list(DataType.text()));
  validator.validateForEntity(entityMeta,tableMetaData);
  pm=completeBean(Void.class,String.class).field("friends").type(LIST).build();
  entityMeta.setPropertyMetas(ImmutableMap.of("friends",pm));
  validator.validateForEntity(entityMeta,tableMetaData);
}
