@Test public void should_duplicate_with_no_data_vut_consistency_and_serial_consistency() throws Exception {
  context.statementWrappers.add(mock(AbstractStatementWrapper.class));
  context.eventHolders.add(mock(EventHolder.class));
  final BatchingFlushContext newContext=context.duplicateWithNoData(ConsistencyLevel.EACH_QUORUM,Optional.fromNullable(com.datastax.driver.core.ConsistencyLevel.LOCAL_SERIAL));
  assertThat(newContext.statementWrappers).isEmpty();
  assertThat(newContext.eventHolders).isEmpty();
  assertThat(newContext.consistencyLevel).isEqualTo(ConsistencyLevel.EACH_QUORUM);
  assertThat(newContext.serialConsistencyLevel.get()).isEqualTo(com.datastax.driver.core.ConsistencyLevel.LOCAL_SERIAL);
}
