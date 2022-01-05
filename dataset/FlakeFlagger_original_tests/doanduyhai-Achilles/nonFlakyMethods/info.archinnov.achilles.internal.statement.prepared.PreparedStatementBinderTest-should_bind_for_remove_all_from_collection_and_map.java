@Test public void should_bind_for_remove_all_from_collection_and_map() throws Exception {
  PropertyMeta idMeta=completeBean(Void.class,Long.class).field("id").transcoder(transcoder).type(ID).invoker(invoker).build();
  EntityMeta meta=new EntityMeta();
  meta.setClassName("CompleteBean");
  meta.setIdMeta(idMeta);
  Long primaryKey=RandomUtils.nextLong();
  when(context.getEntityMeta()).thenReturn(meta);
  when(context.getIdMeta()).thenReturn(idMeta);
  when(context.getPrimaryKey()).thenReturn(primaryKey);
  when(overrider.getWriteLevel(context)).thenReturn(ALL);
  when(invoker.getPrimaryKey(entity,idMeta)).thenReturn(primaryKey);
  when(transcoder.encode(idMeta,primaryKey)).thenReturn(primaryKey);
  when(changeSet.getChangeType()).thenReturn(REMOVE_COLLECTION_OR_MAP);
  when(ps.bind(0,null,primaryKey)).thenReturn(bs);
  final BoundStatementWrapper actual=binder.bindForCollectionAndMapUpdate(context,ps,changeSet);
  verify(bs).setConsistencyLevel(ConsistencyLevel.ALL);
  assertThat(asList(actual.getValues())).containsExactly(0,null,primaryKey);
}
