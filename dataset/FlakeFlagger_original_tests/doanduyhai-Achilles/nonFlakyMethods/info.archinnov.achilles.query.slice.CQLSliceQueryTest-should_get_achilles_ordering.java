@Test public void should_get_achilles_ordering() throws Exception {
  cqlSliceQuery=new CQLSliceQuery<ClusteredEntity>(sliceQuery,EACH_QUORUM);
  assertThat(cqlSliceQuery.getOrdering()).isSameAs(ASCENDING);
}
