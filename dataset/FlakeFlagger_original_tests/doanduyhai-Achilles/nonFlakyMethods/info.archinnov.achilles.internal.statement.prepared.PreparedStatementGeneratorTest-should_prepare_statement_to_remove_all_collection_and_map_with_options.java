@Test public void should_prepare_statement_to_remove_all_collection_and_map_with_options() throws Exception {
  PropertyMeta idMeta=completeBean(Void.class,Long.class).field("id").type(ID).build();
  PropertyMeta setMeta=completeBean(Void.class,String.class).field("followers").type(SET).build();
  EntityMeta meta=new EntityMeta();
  meta.setIdMeta(idMeta);
  meta.setTableName("table");
  meta.setPropertyMetas(ImmutableMap.of("id",idMeta,"followers",setMeta));
  when(session.prepare(regularStatementCaptor.capture())).thenReturn(ps);
  DirtyCheckChangeSet changeSet=new DirtyCheckChangeSet(setMeta,REMOVE_COLLECTION_OR_MAP);
  final PreparedStatement actual=generator.prepareCollectionAndMapUpdate(session,meta,changeSet,ifConditions(new CASCondition("name","John")).withTimestamp(100L));
  assertThat(actual).isSameAs(ps);
  assertThat(regularStatementCaptor.getValue().getQueryString()).isEqualTo("UPDATE table USING TTL :ttl AND TIMESTAMP :timestamp SET followers=:followers WHERE id=:id IF name=:name;");
}
