@Test public void should_get_bound_statement() throws Exception {
  wrapper=new SimpleStatementWrapper("SELECT",values,noListener);
  final SimpleStatement simpleStatement=wrapper.getStatement();
  assertThat(simpleStatement.getQueryString()).isEqualTo("SELECT");
}
