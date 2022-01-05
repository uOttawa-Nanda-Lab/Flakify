@Test public void should_bind_for_remove_entry_from_map_with_cas_condition() throws Exception {
  PropertyMeta idMeta=completeBean(Void.class,Long.class).field("id").transcoder(transcoder).type(ID).invoker(invoker).build();
  Long primaryKey=RandomUtils.nextLong();
  final CASCondition CASCondition=new CASCondition("name","John");
  EntityMeta meta=mock(EntityMeta.class);
  when(meta.getClassName()).thenReturn("CompleteBean");
  when(meta.getIdMeta()).thenReturn(idMeta);
  when(meta.getPrimaryKey(entity)).thenReturn(primaryKey);
  when(meta.encodeCasConditionValue(CASCondition)).thenReturn("John");
  when(context.getEntityMeta()).thenReturn(meta);
  when(context.getIdMeta()).thenReturn(idMeta);
  when(context.getPrimaryKey()).thenReturn(primaryKey);
  when(context.hasCasConditions()).thenReturn(true);
  when(context.getCasConditions()).thenReturn(asList(CASCondition));
  when(overrider.getWriteLevel(context)).thenReturn(ALL);
  when(invoker.getPrimaryKey(entity,idMeta)).thenReturn(primaryKey);
  when(transcoder.encode(idMeta,primaryKey)).thenReturn(primaryKey);
  final Map<Object,Object> values=ImmutableMap.<Object,Object>of(1,"whatever");
  when(changeSet.getChangeType()).thenReturn(REMOVE_FROM_MAP);
  when(changeSet.getEncodedMapChanges()).thenReturn(values);
  when(ps.bind(0,1,null,primaryKey,"John")).thenReturn(bs);
  final BoundStatementWrapper actual=binder.bindForCollectionAndMapUpdate(context,ps,changeSet);
  verify(bs).setConsistencyLevel(ConsistencyLevel.ALL);
  assertThat(asList(actual.getValues())).containsExactly(0,1,null,primaryKey,"John");
}
