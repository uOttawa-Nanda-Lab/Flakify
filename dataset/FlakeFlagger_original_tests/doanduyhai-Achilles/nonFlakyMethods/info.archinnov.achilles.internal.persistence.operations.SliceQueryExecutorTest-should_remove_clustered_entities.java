@Test public void should_remove_clustered_entities() throws Exception {
  sliceQuery=new SliceQuery<>(ClusteredEntity.class,meta,partitionComponents,Arrays.<Object>asList(),Arrays.<Object>asList(),ASCENDING,EXCLUSIVE_BOUNDS,LOCAL_QUORUM,limit,batchSize,false);
  RegularStatementWrapper regularWrapper=mock(RegularStatementWrapper.class);
  when(generator.generateRemoveSliceQuery(anySliceQuery())).thenReturn(regularWrapper);
  executor.remove(sliceQuery);
  verify(daoContext).execute(regularWrapper);
}
