@Test public void should_exception_when_limit_set_for_remove() throws Exception {
  when(sliceQuery.getClusteringsFrom()).thenReturn(Arrays.<Object>asList(11L));
  when(sliceQuery.getClusteringsTo()).thenReturn(Arrays.<Object>asList(11L));
  when(sliceQuery.isLimitSet()).thenReturn(true);
  cqlSliceQuery=new CQLSliceQuery<ClusteredEntity>(sliceQuery,EACH_QUORUM);
  exception.expect(AchillesException.class);
  exception.expectMessage("CQL slice delete does not support LIMIT");
  cqlSliceQuery.validateSliceQueryForRemove();
}
