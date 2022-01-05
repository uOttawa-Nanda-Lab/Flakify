@Test public void should_map_row_to_entity_with_primary_key() throws Exception {
  ClusteredEntity entity=new ClusteredEntity();
  EmbeddedKey embeddedKey=new EmbeddedKey();
  PropertyMeta idMeta=mock(PropertyMeta.class);
  when(idMeta.isEmbeddedId()).thenReturn(true);
  Map<String,PropertyMeta> propertiesMap=new HashMap<String,PropertyMeta>();
  when(row.getColumnDefinitions()).thenReturn(columnDefs);
  when(columnDefs.iterator()).thenReturn(Arrays.<Definition>asList().iterator());
  when(entityMeta.instanciate()).thenReturn(entity);
  when(entityMeta.getIdMeta()).thenReturn(idMeta);
  when(cqlRowInvoker.extractCompoundPrimaryKeyFromRow(row,idMeta,true)).thenReturn(embeddedKey);
  ClusteredEntity actual=entityMapper.mapRowToEntityWithPrimaryKey(entityMeta,row,propertiesMap,true);
  assertThat(actual).isSameAs(entity);
  verify(idMeta).setValueToField(entity,embeddedKey);
}
