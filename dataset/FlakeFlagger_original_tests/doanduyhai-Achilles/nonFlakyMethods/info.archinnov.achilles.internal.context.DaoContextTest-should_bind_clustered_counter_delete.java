@Test public void should_bind_clustered_counter_delete() throws Exception {
  clusteredCounterQueryMap.put(CompleteBean.class,ImmutableMap.<CQLQueryType,Map<String,PreparedStatement>>of(DELETE,of(DELETE_ALL.name(),ps)));
  when(context.getConsistencyLevel()).thenReturn(Optional.<ConsistencyLevel>fromNullable(null));
  when(binder.bindForClusteredCounterDelete(context,ps)).thenReturn(bsWrapper);
  daoContext.bindForClusteredCounterDelete(context);
  verify(context).pushCounterStatement(bsWrapper);
}
