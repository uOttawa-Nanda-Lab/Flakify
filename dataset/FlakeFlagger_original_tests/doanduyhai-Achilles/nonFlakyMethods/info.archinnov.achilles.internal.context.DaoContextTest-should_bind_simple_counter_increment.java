@Test public void should_bind_simple_counter_increment() throws Exception {
  PropertyMeta pm=mock(PropertyMeta.class);
  when(counterQueryMap.get(INCR)).thenReturn(ps);
  when(overrider.getWriteLevel(context,pm)).thenReturn(EACH_QUORUM);
  when(binder.bindForSimpleCounterIncrementDecrement(context,ps,pm,2L,EACH_QUORUM)).thenReturn(bsWrapper);
  daoContext.bindForSimpleCounterIncrement(context,pm,2L);
  verify(context).pushCounterStatement(bsWrapper);
}
