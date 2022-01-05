@Test public void should_get_null_clustered_counter_column() throws Exception {
  PropertyMeta counterMeta=mock(PropertyMeta.class);
  when(counterMeta.getPropertyName()).thenReturn("counter");
  ResultSet resultSet=mock(ResultSet.class);
  Row row=mock(Row.class);
  when(resultSet.one()).thenReturn(row);
  when(row.isNull("counter")).thenReturn(true);
  clusteredCounterQueryMap.put(CompleteBean.class,ImmutableMap.<CQLQueryType,Map<String,PreparedStatement>>of(SELECT,of("counter",ps)));
  when(overrider.getReadLevel(context,counterMeta)).thenReturn(EACH_QUORUM);
  when(binder.bindForClusteredCounterSelect(context,ps,EACH_QUORUM)).thenReturn(bsWrapper);
  when(context.executeImmediate(bsWrapper)).thenReturn(resultSet);
  Long actual=daoContext.getClusteredCounterColumn(context,counterMeta);
  assertThat(actual).isNull();
}
