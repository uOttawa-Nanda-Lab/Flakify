@Test public void should_map_row_to_entity() throws Exception {
  Long id=RandomUtils.nextLong();
  PropertyMeta idMeta=mock(PropertyMeta.class);
  PropertyMeta valueMeta=mock(PropertyMeta.class);
  when(idMeta.isEmbeddedId()).thenReturn(false);
  Map<String,PropertyMeta> propertiesMap=ImmutableMap.of("id",idMeta,"value",valueMeta);
  def1=ColumnDefinitionBuilder.buildColumnDef("keyspace","table","id",DataType.bigint());
  def2=ColumnDefinitionBuilder.buildColumnDef("keyspace","table","value",DataType.text());
  when(row.getColumnDefinitions()).thenReturn(columnDefs);
  when(columnDefs.iterator()).thenReturn(asList(def1,def2).iterator());
  when(entityMeta.getIdMeta()).thenReturn(idMeta);
  when(entityMeta.instanciate()).thenReturn(entity);
  when(cqlRowInvoker.invokeOnRowForFields(row,idMeta)).thenReturn(id);
  when(cqlRowInvoker.invokeOnRowForFields(row,valueMeta)).thenReturn("value");
  when(entityMeta.instanciate()).thenReturn(entity);
  CompleteBean actual=entityMapper.mapRowToEntityWithPrimaryKey(entityMeta,row,propertiesMap,true);
  assertThat(actual).isSameAs(entity);
  verify(idMeta).setValueToField(entity,id);
  verify(valueMeta).setValueToField(entity,"value");
}
