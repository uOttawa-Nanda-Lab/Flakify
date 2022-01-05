@Test public void should_return_null_when_null_row() throws Exception {
  EntityMeta meta=buildEntityMeta();
  String queryString="select id from test";
  initBuilder(queryString,meta,meta.getPropertyMetas(),false);
  when(daoContext.execute(any(AbstractStatementWrapper.class)).one()).thenReturn(null);
  CompleteBean actual=builder.getFirst();
  assertThat(actual).isNull();
  verifyZeroInteractions(contextFactory,proxifier);
  verify(meta,never()).intercept(entity,Event.POST_LOAD);
}
