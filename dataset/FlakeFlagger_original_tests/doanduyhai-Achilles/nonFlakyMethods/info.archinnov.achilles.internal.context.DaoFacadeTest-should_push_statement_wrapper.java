@Test public void should_push_statement_wrapper() throws Exception {
  BoundStatementWrapper bsWrapper=mock(BoundStatementWrapper.class);
  facade.pushStatement(bsWrapper);
  verify(flushContext).pushStatement(bsWrapper);
}
