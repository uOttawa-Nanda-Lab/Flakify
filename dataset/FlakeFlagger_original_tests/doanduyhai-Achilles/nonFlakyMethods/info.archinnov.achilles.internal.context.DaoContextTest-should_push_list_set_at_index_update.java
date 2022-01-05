@Test public void should_push_list_set_at_index_update() throws Exception {
  final Where where=update("test").where();
  Object[] boundValues=new Object[]{"whatever"};
  Pair<Where,Object[]> pair=Pair.create(where,boundValues);
  final Optional<CASResultListener> casResultListener=Optional.absent();
  when(changeSet.getChangeType()).thenReturn(SET_TO_LIST_AT_INDEX);
  when(context.getSerialConsistencyLevel()).thenReturn(fromNullable(com.datastax.driver.core.ConsistencyLevel.LOCAL_SERIAL));
  when(overrider.getWriteLevel(context)).thenReturn(EACH_QUORUM);
  when(statementGenerator.generateCollectionAndMapUpdateOperation(context,changeSet)).thenReturn(pair);
  when(context.getCASResultListener()).thenReturn(casResultListener);
  daoContext.pushCollectionAndMapUpdateStatement(context,changeSet);
  verify(context).pushStatement(statementWrapperCaptor.capture());
  final RegularStatementWrapper statementWrapper=statementWrapperCaptor.getValue();
  assertThat(statementWrapper.getValues()).contains(boundValues);
  assertThat(statementWrapper.getStatement().getConsistencyLevel()).isEqualTo(com.datastax.driver.core.ConsistencyLevel.EACH_QUORUM);
  assertThat(statementWrapper.getStatement().getSerialConsistencyLevel()).isEqualTo(com.datastax.driver.core.ConsistencyLevel.LOCAL_SERIAL);
  assertThat(where.getConsistencyLevel()).isEqualTo(com.datastax.driver.core.ConsistencyLevel.EACH_QUORUM);
}
