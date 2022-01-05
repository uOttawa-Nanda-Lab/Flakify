@Test public void should_push_list_remove_at_index_update() throws Exception {
  final Where where=update("test").where();
  Object[] boundValues=new Object[]{"whatever"};
  Pair<Where,Object[]> pair=Pair.create(where,boundValues);
  final Optional<CASResultListener> casResultListener=Optional.absent();
  when(changeSet.getChangeType()).thenReturn(REMOVE_FROM_LIST_AT_INDEX);
  when(overrider.getWriteLevel(context)).thenReturn(EACH_QUORUM);
  when(statementGenerator.generateCollectionAndMapUpdateOperation(context,changeSet)).thenReturn(pair);
  when(context.getCASResultListener()).thenReturn(casResultListener);
  when(statementGenerator.generateCollectionAndMapUpdateOperation(context,changeSet)).thenReturn(pair);
  daoContext.pushCollectionAndMapUpdateStatement(context,changeSet);
  verify(context).pushStatement(statementWrapperCaptor.capture());
  assertThat(statementWrapperCaptor.getValue().getValues()).contains(boundValues);
  assertThat(where.getConsistencyLevel()).isEqualTo(com.datastax.driver.core.ConsistencyLevel.EACH_QUORUM);
}
