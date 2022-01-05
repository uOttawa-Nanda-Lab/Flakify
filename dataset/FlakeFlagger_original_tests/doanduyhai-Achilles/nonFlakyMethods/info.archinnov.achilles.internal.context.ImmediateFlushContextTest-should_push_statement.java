@Test public void should_push_statement() throws Exception {
  List<AbstractStatementWrapper> statementWrappers=new ArrayList<AbstractStatementWrapper>();
  Whitebox.setInternalState(context,"statementWrappers",statementWrappers);
  context.pushStatement(bsWrapper);
  assertThat(statementWrappers).containsOnly(bsWrapper);
}
