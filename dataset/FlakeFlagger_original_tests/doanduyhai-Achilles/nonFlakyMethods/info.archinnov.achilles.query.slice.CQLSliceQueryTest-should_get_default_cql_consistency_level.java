@Test public void should_get_default_cql_consistency_level() throws Exception {
  when(sliceQuery.getConsistencyLevel()).thenReturn(null);
  cqlSliceQuery=new CQLSliceQuery<ClusteredEntity>(sliceQuery,EACH_QUORUM);
  assertThat(cqlSliceQuery.getConsistencyLevel()).isEqualTo(com.datastax.driver.core.ConsistencyLevel.EACH_QUORUM);
}
