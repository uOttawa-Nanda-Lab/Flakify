@Test public void should_execute_upserts() throws Exception {
  final Object[] boundValues={"test"};
  final Options options=OptionsBuilder.ifNotExists();
  query.boundValues=boundValues;
  query.options=options;
  query.execute();
  verify(daoContext).execute(simpleStatementCaptor.capture());
  final SimpleStatementWrapper actual=simpleStatementCaptor.getValue();
  assertThat(actual.getStatement().getQueryString()).isEqualTo(queryString);
  assertThat(actual.getValues()).isEqualTo(boundValues);
}
