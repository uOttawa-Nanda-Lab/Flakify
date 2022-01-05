@Test public void should_exception_when_varying_components_for_remove() throws Exception {
  when(sliceQuery.getClusteringsFrom()).thenReturn(Arrays.<Object>asList(11L,"a"));
  when(sliceQuery.getClusteringsTo()).thenReturn(Arrays.<Object>asList(11L));
  cqlSliceQuery=new CQLSliceQuery<ClusteredEntity>(sliceQuery,EACH_QUORUM);
  exception.expect(AchillesException.class);
  exception.expectMessage("CQL does not support slice delete with varying compound components");
  cqlSliceQuery.validateSliceQueryForRemove();
}
