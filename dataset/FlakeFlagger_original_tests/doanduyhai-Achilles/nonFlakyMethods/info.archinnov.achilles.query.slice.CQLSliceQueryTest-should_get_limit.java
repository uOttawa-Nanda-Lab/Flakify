@Test public void should_get_limit() throws Exception {
  when(sliceQuery.getLimit()).thenReturn(99);
  cqlSliceQuery=new CQLSliceQuery<ClusteredEntity>(sliceQuery,EACH_QUORUM);
  assertThat(cqlSliceQuery.getLimit()).isEqualTo(99);
}
