@Test public void should_validate_counter_fields() throws Exception {
  PropertyMeta idMeta=completeBean(Void.class,Long.class).field("id").type(ID).build();
  PropertyMeta counter=completeBean(Void.class,Counter.class).field("count").type(COUNTER).build();
  entityMeta.setIdMeta(idMeta);
  entityMeta.setAllMetasExceptId(Arrays.asList(counter));
  entityMeta.setAllMetasExceptIdAndCounters(Arrays.<PropertyMeta>asList());
  entityMeta.setAllMetasExceptCounters(Arrays.<PropertyMeta>asList());
  entityMeta.setPropertyMetas(ImmutableMap.of("count",counter));
  when(tableMetaData.getName()).thenReturn("table");
  when(tableMetaData.getColumn("id")).thenReturn(columnMetadata);
  when(columnMetadata.getType()).thenReturn(DataType.bigint());
  when(columnMetadata.getIndex()).thenReturn(null);
  when(tableMetaData.getColumn("count")).thenReturn(columnMetadataForField);
  when(columnMetadataForField.getType()).thenReturn(DataType.counter());
  when(columnMetadataForField.getIndex()).thenReturn(null);
  validator.validateForEntity(entityMeta,tableMetaData);
}
