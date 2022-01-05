@Test public void should_get_cql_consistency_level() throws Exception {
  when(sliceQuery.getConsistencyLevel()).thenReturn(LOCAL_QUORUM);
  cqlSliceQuery=new CQLSliceQuery<ClusteredEntity>(sliceQuery,EACH_QUORUM);
  assertThat(cqlSliceQuery.getConsistencyLevel()).isEqualTo(com.datastax.driver.core.ConsistencyLevel.LOCAL_QUORUM);
}
