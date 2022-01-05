@Test public void should_bind_for_clustered_counter_delete() throws Exception {
  PropertyMeta idMeta=completeBean(Void.class,Long.class).field("id").transcoder(transcoder).type(ID).invoker(invoker).build();
  EntityMeta meta=new EntityMeta();
  meta.setClassName("CompleteBean");
  meta.setIdMeta(idMeta);
  Long primaryKey=RandomUtils.nextLong();
  when(context.getEntityMeta()).thenReturn(meta);
  when(context.getIdMeta()).thenReturn(idMeta);
  when(context.getPrimaryKey()).thenReturn(primaryKey);
  when(overrider.getWriteLevel(context)).thenReturn(ALL);
  when(transcoder.encode(idMeta,primaryKey)).thenReturn(primaryKey);
  when(ps.bind(primaryKey)).thenReturn(bs);
  BoundStatementWrapper actual=binder.bindForClusteredCounterDelete(context,ps);
  verify(bs).setConsistencyLevel(ConsistencyLevel.ALL);
  assertThat(asList(actual.getValues())).containsExactly(primaryKey);
}
