@Test public void should_get_simple_counter() throws Exception {
  ResultSet resultSet=mock(ResultSet.class);
  Row row=mock(Row.class);
  PropertyMeta pm=PropertyMetaTestBuilder.valueClass(String.class).field("name").consistencyLevels(Pair.create(EACH_QUORUM,EACH_QUORUM)).build();
  when(counterQueryMap.get(CQLQueryType.SELECT)).thenReturn(ps);
  when(binder.bindForSimpleCounterSelect(context,ps,pm,EACH_QUORUM)).thenReturn(bsWrapper);
  when(context.executeImmediate(bsWrapper)).thenReturn(resultSet);
  when(resultSet.all()).thenReturn(asList(row));
  Row actual=daoContext.getSimpleCounter(context,pm,EACH_QUORUM);
  assertThat(actual).isSameAs(row);
}
