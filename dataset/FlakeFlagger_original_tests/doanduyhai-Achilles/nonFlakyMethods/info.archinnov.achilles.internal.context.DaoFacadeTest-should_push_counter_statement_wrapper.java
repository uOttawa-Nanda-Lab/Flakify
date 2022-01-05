@Test public void should_push_counter_statement_wrapper() throws Exception {
  BoundStatementWrapper bsWrapper=mock(BoundStatementWrapper.class);
  facade.pushCounterStatement(bsWrapper);
  verify(flushContext).pushCounterStatement(bsWrapper);
}
