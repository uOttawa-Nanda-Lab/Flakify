@Test public void should_get_ordering_asc() throws Exception {
  when(sliceQuery.getClusteringsFrom()).thenReturn(Arrays.<Object>asList(11L));
  when(sliceQuery.getClusteringsTo()).thenReturn(Arrays.<Object>asList(11L));
  when(sliceQuery.getMeta().getIdMeta().getOrderingComponent()).thenReturn("orderingComp");
  when(sliceQuery.getOrdering()).thenReturn(ASCENDING);
  cqlSliceQuery=new CQLSliceQuery<ClusteredEntity>(sliceQuery,EACH_QUORUM);
  Ordering ordering=cqlSliceQuery.getCQLOrdering();
  assertThat(Whitebox.getInternalState(ordering,"name")).isEqualTo("orderingComp");
  assertThat((Boolean)Whitebox.getInternalState(ordering,"isDesc")).isFalse();
}
