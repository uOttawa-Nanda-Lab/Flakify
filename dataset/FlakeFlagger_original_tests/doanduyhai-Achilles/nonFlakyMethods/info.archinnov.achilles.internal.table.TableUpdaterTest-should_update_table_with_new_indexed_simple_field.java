@Test public void should_update_table_with_new_indexed_simple_field() throws Exception {
  PropertyMeta idMeta=PropertyMetaTestBuilder.valueClass(Long.class).type(ID).field("id").build();
  PropertyMeta longColPM=PropertyMetaTestBuilder.valueClass(Long.class).type(SIMPLE).field("longCol").build();
  longColPM.setIndexProperties(new IndexProperties("long_index","longCol"));
  meta=new EntityMeta();
  meta.setAllMetasExceptId(asList(longColPM));
  meta.setIdMeta(idMeta);
  meta.setTableName("tableName");
  meta.setClassName("entityName");
  meta.setSchemaUpdateEnabled(true);
  updater.updateTableForEntity(session,meta,tableMeta);
  verify(session,Mockito.times(2)).execute(stringCaptor.capture());
  final List<String> updates=stringCaptor.getAllValues();
  assertThat(updates.get(0)).isEqualTo("\n\tALTER TABLE tableName ADD longCol bigint");
  assertThat(updates.get(1)).isEqualTo("\n\tCREATE INDEX long_index ON tableName(longCol)");
}
