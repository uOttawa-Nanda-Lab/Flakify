@Test public void should_update_table_with_new_map_field() throws Exception {
  PropertyMeta idMeta=PropertyMetaTestBuilder.valueClass(Long.class).type(ID).field("id").build();
  PropertyMeta listStringPM=PropertyMetaTestBuilder.completeBean(Integer.class,String.class).type(MAP).field("preferences").build();
  meta=new EntityMeta();
  meta.setAllMetasExceptId(asList(listStringPM));
  meta.setIdMeta(idMeta);
  meta.setTableName("tableName");
  meta.setClassName("entityName");
  meta.setSchemaUpdateEnabled(true);
  updater.updateTableForEntity(session,meta,tableMeta);
  verify(session).execute(stringCaptor.capture());
  assertThat(stringCaptor.getValue()).isEqualTo("\n\tALTER TABLE tableName ADD preferences map<int, text>");
}
