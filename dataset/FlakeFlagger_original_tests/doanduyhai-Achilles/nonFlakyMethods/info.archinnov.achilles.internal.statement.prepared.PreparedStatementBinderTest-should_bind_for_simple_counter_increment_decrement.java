@Test public void should_bind_for_simple_counter_increment_decrement() throws Exception {
  PropertyMeta idMeta=completeBean(Void.class,Long.class).field("id").transcoder(transcoder).invoker(invoker).build();
  EntityMeta meta=new EntityMeta();
  meta.setClassName("CompleteBean");
  meta.setIdMeta(idMeta);
  PropertyMeta counterMeta=completeBean(Void.class,Long.class).field("count").transcoder(transcoder).invoker(invoker).build();
  Long primaryKey=RandomUtils.nextLong();
  Long counter=RandomUtils.nextLong();
  when(context.getEntityMeta()).thenReturn(meta);
  when(context.getIdMeta()).thenReturn(idMeta);
  when(context.getPrimaryKey()).thenReturn(primaryKey);
  when(transcoder.forceEncodeToJSON(primaryKey)).thenReturn(primaryKey.toString());
  when(transcoder.forceEncodeToJSON(counter)).thenReturn(counter.toString());
  when(overrider.getWriteLevel(context)).thenReturn(ALL);
  when(ps.bind(counter,"CompleteBean",primaryKey.toString(),"count")).thenReturn(bs);
  BoundStatementWrapper actual=binder.bindForSimpleCounterIncrementDecrement(context,ps,counterMeta,counter,ALL);
  verify(bs).setConsistencyLevel(ConsistencyLevel.ALL);
  assertThat(asList(actual.getValues())).containsExactly(counter,"CompleteBean",primaryKey.toString(),"count");
}
