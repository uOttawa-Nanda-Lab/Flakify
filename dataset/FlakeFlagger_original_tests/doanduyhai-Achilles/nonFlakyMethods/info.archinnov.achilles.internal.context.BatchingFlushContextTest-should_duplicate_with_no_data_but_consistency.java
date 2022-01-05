@Test public void should_duplicate_with_no_data_but_consistency() throws Exception {
  context.statementWrappers.add(mock(AbstractStatementWrapper.class));
  context.eventHolders.add(mock(EventHolder.class));
  final BatchingFlushContext newContext=context.duplicateWithNoData(ConsistencyLevel.EACH_QUORUM);
  assertThat(newContext.statementWrappers).isEmpty();
  assertThat(newContext.eventHolders).isEmpty();
  assertThat(newContext.consistencyLevel).isEqualTo(ConsistencyLevel.EACH_QUORUM);
}
