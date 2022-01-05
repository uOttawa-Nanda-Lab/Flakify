@Test public void should_load_property() throws Exception {
  PropertyMeta pm=mock(PropertyMeta.class);
  ResultSet resultSet=mock(ResultSet.class);
  Row row=mock(Row.class);
  when(cacheManager.getCacheForFieldSelect(session,dynamicPSCache,context,pm)).thenReturn(ps);
  when(overrider.getReadLevel(context)).thenReturn(EACH_QUORUM);
  when(binder.bindStatementWithOnlyPKInWhereClause(context,ps,EACH_QUORUM)).thenReturn(bsWrapper);
  when(resultSet.all()).thenReturn(asList(row));
  when(context.executeImmediate(bsWrapper)).thenReturn(resultSet);
  Row actual=daoContext.loadProperty(context,pm);
  assertThat(actual).isSameAs(row);
}
