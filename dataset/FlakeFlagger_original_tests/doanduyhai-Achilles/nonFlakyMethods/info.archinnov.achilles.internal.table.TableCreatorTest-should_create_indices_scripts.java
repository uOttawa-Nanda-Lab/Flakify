@Test public void should_create_indices_scripts() throws Exception {
  PropertyMeta idMeta=PropertyMetaTestBuilder.valueClass(Long.class).type(ID).field("id").build();
  PropertyMeta longColPM=PropertyMetaTestBuilder.valueClass(Long.class).type(SIMPLE).field("longCol").build();
  longColPM.setIndexProperties(new IndexProperties("",""));
  meta=new EntityMeta();
  meta.setAllMetasExceptIdAndCounters(asList(longColPM));
  meta.setIdMeta(idMeta);
  meta.setTableName("tableName");
  meta.setClassName("entityName");
  creator.createTableForEntity(session,meta,true);
  verify(session,new Times(2)).execute(stringCaptor.capture());
  assertThat(stringCaptor.getValue()).isEqualTo("\n\tCREATE INDEX tableName_longCol ON tableName(longCol)");
}
