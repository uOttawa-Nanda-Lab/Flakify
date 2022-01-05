@Test public void should_eager_load_entity() throws Exception {
  ResultSet resultSet=mock(ResultSet.class);
  Row row=mock(Row.class);
  when(context.<CompleteBean>getEntityClass()).thenReturn(CompleteBean.class);
  when(selectEagerPSs.get(CompleteBean.class)).thenReturn(ps);
  when(overrider.getReadLevel(context)).thenReturn(LOCAL_QUORUM);
  when(binder.bindStatementWithOnlyPKInWhereClause(context,ps,LOCAL_QUORUM)).thenReturn(bsWrapper);
  when(resultSet.all()).thenReturn(asList(row));
  when(context.executeImmediate(bsWrapper)).thenReturn(resultSet);
  Row actual=daoContext.loadEntity(context);
  assertThat(actual).isSameAs(row);
}
