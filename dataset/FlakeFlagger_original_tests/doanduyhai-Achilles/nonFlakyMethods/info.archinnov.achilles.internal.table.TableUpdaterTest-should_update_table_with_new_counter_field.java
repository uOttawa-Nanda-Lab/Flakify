@Test public void should_update_table_with_new_counter_field() throws Exception {
  PropertyMeta idMeta=PropertyMetaTestBuilder.valueClass(Long.class).type(ID).field("id").build();
  PropertyMeta longColPM=PropertyMetaTestBuilder.valueClass(Counter.class).type(COUNTER).field("count").build();
  meta=new EntityMeta();
  meta.setAllMetasExceptId(asList(longColPM));
  meta.setIdMeta(idMeta);
  meta.setTableName("tableName");
  meta.setClassName("entityName");
  meta.setSchemaUpdateEnabled(true);
  updater.updateTableForEntity(session,meta,tableMeta);
  verify(session).execute(stringCaptor.capture());
  assertThat(stringCaptor.getValue()).isEqualTo("\n\tALTER TABLE tableName ADD count counter");
}
