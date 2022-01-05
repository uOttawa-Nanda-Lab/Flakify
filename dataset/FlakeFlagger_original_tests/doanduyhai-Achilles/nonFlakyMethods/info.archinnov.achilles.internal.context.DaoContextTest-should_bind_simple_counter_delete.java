@Test public void should_bind_simple_counter_delete() throws Exception {
  PropertyMeta pm=PropertyMetaTestBuilder.valueClass(String.class).field("name").consistencyLevels(Pair.create(EACH_QUORUM,EACH_QUORUM)).build();
  when(context.getConsistencyLevel()).thenReturn(Optional.<ConsistencyLevel>fromNullable(null));
  when(counterQueryMap.get(CQLQueryType.DELETE)).thenReturn(ps);
  when(binder.bindForSimpleCounterDelete(context,ps,pm)).thenReturn(bsWrapper);
  daoContext.bindForSimpleCounterDelete(context,pm);
  verify(context).pushCounterStatement(bsWrapper);
}
