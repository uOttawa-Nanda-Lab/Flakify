@Test public void should_bind_clustered_counter_delete_with_runtime_consistency() throws Exception {
  clusteredCounterQueryMap.put(CompleteBean.class,ImmutableMap.<CQLQueryType,Map<String,PreparedStatement>>of(DELETE,of(DELETE_ALL.name(),ps)));
  when(context.getConsistencyLevel()).thenReturn(fromNullable(LOCAL_QUORUM));
  when(binder.bindForClusteredCounterDelete(context,ps)).thenReturn(bsWrapper);
  daoContext.bindForClusteredCounterDelete(context);
  verify(context).pushCounterStatement(bsWrapper);
}
