@Test public void should_prepare_statement_to_remove_entry_from_map() throws Exception {
  PropertyMeta idMeta=completeBean(Void.class,Long.class).field("id").type(ID).build();
  PropertyMeta mapMeta=completeBean(Integer.class,String.class).field("preferences").type(MAP).build();
  EntityMeta meta=new EntityMeta();
  meta.setIdMeta(idMeta);
  meta.setTableName("table");
  meta.setPropertyMetas(ImmutableMap.of("id",idMeta,"preferences",mapMeta));
  when(session.prepare(regularStatementCaptor.capture())).thenReturn(ps);
  DirtyCheckChangeSet changeSet=new DirtyCheckChangeSet(mapMeta,REMOVE_FROM_MAP);
  Whitebox.setInternalState(changeSet,"mapChanges",ImmutableMap.of(1,"a"));
  final PreparedStatement actual=generator.prepareCollectionAndMapUpdate(session,meta,changeSet,noOptions());
  assertThat(actual).isSameAs(ps);
  assertThat(regularStatementCaptor.getValue().getQueryString()).isEqualTo("UPDATE table USING TTL :ttl SET preferences[:key]=:nullValue WHERE id=:id;");
}
