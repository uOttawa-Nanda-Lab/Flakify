@Test public void should_get_clustered_counter() throws Exception {
  ResultSet resultSet=mock(ResultSet.class);
  Row row=mock(Row.class);
  clusteredCounterQueryMap.put(CompleteBean.class,ImmutableMap.<CQLQueryType,Map<String,PreparedStatement>>of(SELECT,of(SELECT_ALL.name(),ps)));
  when(overrider.getReadLevel(context)).thenReturn(EACH_QUORUM);
  when(binder.bindForClusteredCounterSelect(context,ps,EACH_QUORUM)).thenReturn(bsWrapper);
  when(context.executeImmediate(bsWrapper)).thenReturn(resultSet);
  when(resultSet.all()).thenReturn(asList(row));
  Row actual=daoContext.getClusteredCounter(context);
  assertThat(actual).isSameAs(row);
}
