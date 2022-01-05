@Test public void should_prepare_statement_to_append_elements_to_list() throws Exception {
  PropertyMeta idMeta=completeBean(Void.class,Long.class).field("id").type(ID).build();
  PropertyMeta listMeta=completeBean(Void.class,String.class).field("friends").type(LIST).build();
  EntityMeta meta=new EntityMeta();
  meta.setIdMeta(idMeta);
  meta.setTableName("table");
  meta.setPropertyMetas(ImmutableMap.of("id",idMeta,"friends",listMeta));
  when(session.prepare(regularStatementCaptor.capture())).thenReturn(ps);
  DirtyCheckChangeSet changeSet=new DirtyCheckChangeSet(listMeta,APPEND_TO_LIST);
  final PreparedStatement actual=generator.prepareCollectionAndMapUpdate(session,meta,changeSet,noOptions());
  assertThat(actual).isSameAs(ps);
  assertThat(regularStatementCaptor.getValue().getQueryString()).isEqualTo("UPDATE table USING TTL :ttl SET friends=friends+:friends WHERE id=:id;");
}
