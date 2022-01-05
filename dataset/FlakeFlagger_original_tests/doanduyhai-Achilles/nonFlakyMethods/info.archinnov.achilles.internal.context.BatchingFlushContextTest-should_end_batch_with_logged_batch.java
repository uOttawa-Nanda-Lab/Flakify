@Test public void should_end_batch_with_logged_batch() throws Exception {
  EventHolder eventHolder=mock(EventHolder.class);
  RegularStatement statement1=QueryBuilder.select().from("table1");
  RegularStatement statement2=QueryBuilder.select().from("table2");
  AbstractStatementWrapper wrapper1=new RegularStatementWrapper(CompleteBean.class,statement1,null,com.datastax.driver.core.ConsistencyLevel.ONE,NO_LISTENER,NO_SERIAL_CONSISTENCY);
  AbstractStatementWrapper wrapper2=new RegularStatementWrapper(CompleteBean.class,statement2,null,com.datastax.driver.core.ConsistencyLevel.ONE,NO_LISTENER,NO_SERIAL_CONSISTENCY);
  context.eventHolders=Arrays.asList(eventHolder);
  context.statementWrappers=Arrays.asList(wrapper1,wrapper2);
  context.counterStatementWrappers=Arrays.asList(wrapper1,wrapper2);
  context.consistencyLevel=ConsistencyLevel.LOCAL_QUORUM;
  context.serialConsistencyLevel=Optional.fromNullable(com.datastax.driver.core.ConsistencyLevel.LOCAL_SERIAL);
  context.endBatch();
  verify(eventHolder).triggerInterception();
  verify(daoContext,times(2)).executeBatch(batchCaptor.capture());
  assertThat(batchCaptor.getAllValues()).hasSize(2);
  final BatchStatement batchStatement1=batchCaptor.getAllValues().get(0);
  assertThat(batchStatement1.getConsistencyLevel()).isSameAs(com.datastax.driver.core.ConsistencyLevel.LOCAL_QUORUM);
  assertThat(batchStatement1.getSerialConsistencyLevel()).isSameAs(com.datastax.driver.core.ConsistencyLevel.LOCAL_SERIAL);
  final List<Statement> statements1=WhiteboxImpl.getInternalState(batchStatement1,"statements");
  assertThat(statements1).contains(statement1,statement2);
  final BatchStatement batchStatement2=batchCaptor.getAllValues().get(1);
  assertThat(batchStatement2.getConsistencyLevel()).isSameAs(com.datastax.driver.core.ConsistencyLevel.LOCAL_QUORUM);
  assertThat(batchStatement2.getSerialConsistencyLevel()).isSameAs(com.datastax.driver.core.ConsistencyLevel.LOCAL_SERIAL);
  final List<Statement> statements2=WhiteboxImpl.getInternalState(batchStatement2,"statements");
  assertThat(statements2).contains(statement1,statement2);
}
