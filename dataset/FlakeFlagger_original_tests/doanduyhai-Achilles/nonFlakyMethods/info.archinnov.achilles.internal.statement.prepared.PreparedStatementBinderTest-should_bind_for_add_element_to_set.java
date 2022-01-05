@Test public void should_bind_for_add_element_to_set() throws Exception {
  PropertyMeta idMeta=completeBean(Void.class,Long.class).field("id").transcoder(transcoder).type(ID).invoker(invoker).build();
  EntityMeta meta=new EntityMeta();
  meta.setClassName("CompleteBean");
  meta.setIdMeta(idMeta);
  Long primaryKey=RandomUtils.nextLong();
  when(context.getEntityMeta()).thenReturn(meta);
  when(context.getIdMeta()).thenReturn(idMeta);
  when(context.getPrimaryKey()).thenReturn(primaryKey);
  when(context.getSerialConsistencyLevel()).thenReturn(fromNullable(ConsistencyLevel.LOCAL_SERIAL));
  when(overrider.getWriteLevel(context)).thenReturn(ALL);
  when(invoker.getPrimaryKey(entity,idMeta)).thenReturn(primaryKey);
  when(transcoder.encode(idMeta,primaryKey)).thenReturn(primaryKey);
  final Set<Object> values=Sets.<Object>newHashSet("whatever");
  when(changeSet.getChangeType()).thenReturn(ADD_TO_SET);
  when(changeSet.getEncodedSetChanges()).thenReturn(values);
  when(ps.bind(0,values,primaryKey)).thenReturn(bs);
  final BoundStatementWrapper actual=binder.bindForCollectionAndMapUpdate(context,ps,changeSet);
  verify(bs).setConsistencyLevel(ConsistencyLevel.ALL);
  verify(bs).setSerialConsistencyLevel(ConsistencyLevel.LOCAL_SERIAL);
  assertThat(asList(actual.getValues())).containsExactly(0,values,primaryKey);
}
