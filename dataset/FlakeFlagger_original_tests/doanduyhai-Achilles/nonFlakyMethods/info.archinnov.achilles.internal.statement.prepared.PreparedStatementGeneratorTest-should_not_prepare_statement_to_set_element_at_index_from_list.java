@Test(expected=IllegalStateException.class) public void should_not_prepare_statement_to_set_element_at_index_from_list() throws Exception {
  PropertyMeta idMeta=completeBean(Void.class,Long.class).field("id").type(ID).build();
  PropertyMeta listMeta=completeBean(Void.class,String.class).field("friends").type(LIST).build();
  EntityMeta meta=new EntityMeta();
  meta.setIdMeta(idMeta);
  meta.setTableName("table");
  meta.setPropertyMetas(ImmutableMap.of("id",idMeta,"friends",listMeta));
  when(session.prepare(regularStatementCaptor.capture())).thenReturn(ps);
  DirtyCheckChangeSet changeSet=new DirtyCheckChangeSet(listMeta,SET_TO_LIST_AT_INDEX);
  generator.prepareCollectionAndMapUpdate(session,meta,changeSet,noOptions());
}
