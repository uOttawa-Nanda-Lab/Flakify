@Test public void should_validate_map_field_for_entity() throws Exception {
  PropertyMeta idMeta=completeBean(Void.class,Long.class).field("id").type(ID).build();
  PropertyMeta pm=completeBean(Integer.class,String.class).field("preferences").type(MAP).build();
  entityMeta.setIdMeta(idMeta);
  entityMeta.setAllMetasExceptIdAndCounters(Arrays.asList(pm));
  entityMeta.setPropertyMetas(Maps.<String,PropertyMeta>newHashMap());
  when(tableMetaData.getName()).thenReturn("table");
  when(tableMetaData.getColumn("id")).thenReturn(columnMetadata);
  when(columnMetadata.getType()).thenReturn(DataType.bigint());
  when(tableMetaData.getColumn("preferences")).thenReturn(columnMetadataForField);
  when(columnMetadataForField.getType()).thenReturn(DataType.map(DataType.cint(),DataType.text()));
  validator.validateForEntity(entityMeta,tableMetaData);
  pm=completeBean(Integer.class,String.class).field("preferences").type(MAP).build();
  entityMeta.setPropertyMetas(ImmutableMap.of("preferences",pm));
  validator.validateForEntity(entityMeta,tableMetaData);
}
