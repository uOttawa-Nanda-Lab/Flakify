@Test public void should_bind_for_assign_value_to_map() throws Exception {
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
  final Map<Object,Object> values=ImmutableMap.<Object,Object>of(1,"whatever");
  when(changeSet.getChangeType()).thenReturn(ASSIGN_VALUE_TO_MAP);
  when(changeSet.getEncodedMapChanges()).thenReturn(values);
  when(ps.bind(0,values,primaryKey)).thenReturn(bs);
  final BoundStatementWrapper actual=binder.bindForCollectionAndMapUpdate(context,ps,changeSet);
  verify(bs).setConsistencyLevel(ConsistencyLevel.ALL);
  assertThat(asList(actual.getValues())).containsExactly(0,values,primaryKey);
}
