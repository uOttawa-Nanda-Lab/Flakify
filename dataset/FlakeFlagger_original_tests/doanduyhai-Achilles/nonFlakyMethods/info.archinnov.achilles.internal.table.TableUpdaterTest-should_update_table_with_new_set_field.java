@Test public void should_update_table_with_new_set_field() throws Exception {
  PropertyMeta idMeta=PropertyMetaTestBuilder.valueClass(Long.class).type(ID).field("id").build();
  PropertyMeta listStringPM=PropertyMetaTestBuilder.valueClass(String.class).type(SET).field("set_string").build();
  meta=new EntityMeta();
  meta.setAllMetasExceptId(asList(listStringPM));
  meta.setIdMeta(idMeta);
  meta.setTableName("tableName");
  meta.setClassName("entityName");
  meta.setSchemaUpdateEnabled(true);
  updater.updateTableForEntity(session,meta,tableMeta);
  verify(session).execute(stringCaptor.capture());
  assertThat(stringCaptor.getValue()).isEqualTo("\n\tALTER TABLE tableName ADD set_string set<text>");
}
