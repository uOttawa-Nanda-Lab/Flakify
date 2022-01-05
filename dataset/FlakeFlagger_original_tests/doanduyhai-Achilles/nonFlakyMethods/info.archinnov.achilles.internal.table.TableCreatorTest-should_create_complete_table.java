@Test public void should_create_complete_table() throws Exception {
  PropertyMeta idMeta=PropertyMetaTestBuilder.valueClass(Long.class).type(ID).field("id").build();
  PropertyMeta longColPM=PropertyMetaTestBuilder.valueClass(Long.class).type(SIMPLE).field("longCol").build();
  PropertyMeta longListColPM=PropertyMetaTestBuilder.valueClass(Long.class).type(LIST).field("longListCol").build();
  PropertyMeta longSetColPM=PropertyMetaTestBuilder.valueClass(Long.class).type(SET).field("longSetCol").build();
  PropertyMeta longMapColPM=PropertyMetaTestBuilder.keyValueClass(Integer.class,Long.class).type(MAP).field("longMapCol").build();
  meta=new EntityMeta();
  meta.setAllMetasExceptIdAndCounters(asList(longColPM,longListColPM,longSetColPM,longMapColPM));
  meta.setIdMeta(idMeta);
  meta.setTableName("tableName");
  meta.setClassName("entityName");
  creator.createTableForEntity(session,meta,true);
  verify(session).execute(stringCaptor.capture());
  assertThat(stringCaptor.getValue()).isEqualTo("\n\tCREATE TABLE tableName(\n" + "\t\tid bigint,\n" + "\t\tlongCol bigint,\n"+ "\t\tlongListCol list<bigint>,\n"+ "\t\tlongSetCol set<bigint>,\n"+ "\t\tlongMapCol map<int,bigint>,\n"+ "\t\tPRIMARY KEY(id))\n"+ "\tWITH comment = 'Create table for entity \"entityName\"'");
}
