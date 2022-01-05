@Test public void should_bind_for_simple_counter_delete() throws Exception {
  PropertyMeta idMeta=completeBean(Void.class,Long.class).field("id").transcoder(transcoder).invoker(invoker).build();
  EntityMeta meta=new EntityMeta();
  meta.setClassName("CompleteBean");
  meta.setIdMeta(idMeta);
  PropertyMeta counterMeta=completeBean(Void.class,Long.class).field("count").transcoder(transcoder).invoker(invoker).build();
  Long primaryKey=RandomUtils.nextLong();
  when(context.getEntityMeta()).thenReturn(meta);
  when(context.getIdMeta()).thenReturn(idMeta);
  when(context.getPrimaryKey()).thenReturn(primaryKey);
  when(overrider.getWriteLevel(context)).thenReturn(ALL);
  when(transcoder.forceEncodeToJSON(primaryKey)).thenReturn(primaryKey.toString());
  when(ps.bind("CompleteBean",primaryKey.toString(),"count")).thenReturn(bs);
  BoundStatementWrapper actual=binder.bindForSimpleCounterDelete(context,ps,counterMeta);
  verify(bs).setConsistencyLevel(ConsistencyLevel.ALL);
  assertThat(asList(actual.getValues())).containsExactly("CompleteBean",primaryKey.toString(),"count");
}
