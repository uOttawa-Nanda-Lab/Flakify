@Test public void should_decrement_simple_counter() throws Exception {
  Long counterValue=RandomUtils.nextLong();
  PropertyMeta pm=PropertyMetaTestBuilder.valueClass(String.class).field("name").build();
  when(counterQueryMap.get(CQLQueryType.DECR)).thenReturn(ps);
  when(binder.bindForSimpleCounterIncrementDecrement(context,ps,pm,counterValue,EACH_QUORUM)).thenReturn(bsWrapper);
  daoContext.decrementSimpleCounter(context,pm,counterValue,EACH_QUORUM);
  verify(context).executeImmediate(bsWrapper);
}
