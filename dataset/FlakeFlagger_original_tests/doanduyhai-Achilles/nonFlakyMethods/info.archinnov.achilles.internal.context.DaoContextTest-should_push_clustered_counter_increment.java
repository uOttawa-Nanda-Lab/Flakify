@Test public void should_push_clustered_counter_increment() throws Exception {
  PropertyMeta counterMeta=PropertyMetaTestBuilder.valueClass(Long.class).field("count").consistencyLevels(Pair.create(EACH_QUORUM,EACH_QUORUM)).build();
  when(context.getTtl()).thenReturn(Optional.<Integer>absent());
  when(context.getConsistencyLevel()).thenReturn(Optional.<ConsistencyLevel>fromNullable(null));
  clusteredCounterQueryMap.put(CompleteBean.class,ImmutableMap.<CQLQueryType,Map<String,PreparedStatement>>of(INCR,of("count",ps)));
  when(binder.bindForClusteredCounterIncrementDecrement(context,ps,2L)).thenReturn(bsWrapper);
  daoContext.pushClusteredCounterIncrementStatement(context,counterMeta,2L);
  verify(context).pushCounterStatement(bsWrapper);
}
