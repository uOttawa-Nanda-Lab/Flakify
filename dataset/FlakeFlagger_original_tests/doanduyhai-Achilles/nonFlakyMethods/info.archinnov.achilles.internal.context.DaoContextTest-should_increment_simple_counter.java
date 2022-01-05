@Test public void should_increment_simple_counter() throws Exception {
  Long counterValue=RandomUtils.nextLong();
  PropertyMeta pm=PropertyMetaTestBuilder.valueClass(String.class).field("name").build();
  when(counterQueryMap.get(INCR)).thenReturn(ps);
  when(binder.bindForSimpleCounterIncrementDecrement(context,ps,pm,counterValue,EACH_QUORUM)).thenReturn(bsWrapper);
  daoContext.incrementSimpleCounter(context,pm,counterValue,EACH_QUORUM);
  verify(context).executeImmediate(bsWrapper);
}
