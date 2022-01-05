@Test public void should_get_bounding() throws Exception {
  when(sliceQuery.getBounding()).thenReturn(INCLUSIVE_END_BOUND_ONLY);
  cqlSliceQuery=new CQLSliceQuery<ClusteredEntity>(sliceQuery,EACH_QUORUM);
  assertThat(cqlSliceQuery.getBounding()).isEqualTo(INCLUSIVE_END_BOUND_ONLY);
}
