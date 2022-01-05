@Test public void should_execute() throws Exception {
  wrapper=new SimpleStatementWrapper("SELECT",values,noListener);
  wrapper.execute(session);
  verify(session).execute(statementCaptor.capture());
  final RegularStatement regularStatement=statementCaptor.getValue();
  assertThat(regularStatement.getQueryString()).isEqualTo("SELECT");
}
