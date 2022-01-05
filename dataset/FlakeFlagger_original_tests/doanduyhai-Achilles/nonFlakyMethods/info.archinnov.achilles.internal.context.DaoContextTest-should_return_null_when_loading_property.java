@Test public void should_return_null_when_loading_property() throws Exception {
  ResultSet resultSet=mock(ResultSet.class);
  PropertyMeta pm=mock(PropertyMeta.class);
  when(cacheManager.getCacheForFieldSelect(session,dynamicPSCache,context,pm)).thenReturn(ps);
  when(overrider.getReadLevel(context)).thenReturn(EACH_QUORUM);
  when(binder.bindStatementWithOnlyPKInWhereClause(context,ps,EACH_QUORUM)).thenReturn(bsWrapper);
  when(resultSet.all()).thenReturn(Lists.<Row>newLinkedList());
  when(context.executeImmediate(bsWrapper)).thenReturn(resultSet);
  assertThat(daoContext.loadProperty(context,pm)).isNull();
}
