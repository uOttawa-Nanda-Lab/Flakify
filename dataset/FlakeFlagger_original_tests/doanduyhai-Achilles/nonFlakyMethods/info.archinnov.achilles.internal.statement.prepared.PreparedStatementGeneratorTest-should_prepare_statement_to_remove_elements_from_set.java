@Test public void should_prepare_statement_to_remove_elements_from_set() throws Exception {
  PropertyMeta idMeta=completeBean(Void.class,Long.class).field("id").type(ID).build();
  PropertyMeta setMeta=completeBean(Void.class,String.class).field("followers").type(SET).build();
  EntityMeta meta=new EntityMeta();
  meta.setIdMeta(idMeta);
  meta.setTableName("table");
  meta.setPropertyMetas(ImmutableMap.of("id",idMeta,"followers",setMeta));
  when(session.prepare(regularStatementCaptor.capture())).thenReturn(ps);
  DirtyCheckChangeSet changeSet=new DirtyCheckChangeSet(setMeta,REMOVE_FROM_SET);
  final PreparedStatement actual=generator.prepareCollectionAndMapUpdate(session,meta,changeSet,noOptions());
  assertThat(actual).isSameAs(ps);
  assertThat(regularStatementCaptor.getValue().getQueryString()).isEqualTo("UPDATE table USING TTL :ttl SET followers=followers-:followers WHERE id=:id;");
}
