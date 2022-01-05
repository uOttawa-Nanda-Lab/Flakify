@Test public void should_override_batch_level_by_runtime_value_for_slice_query() throws Exception {
  Batch batchEm=pmf.createBatch();
  batchEm.startBatch(ONE);
  expectedEx.expect(InvalidQueryException.class);
  expectedEx.expectMessage("EACH_QUORUM ConsistencyLevel is only supported for writes");
  batchEm.sliceQuery(ClusteredEntity.class).partitionComponents(11L).consistencyLevel(EACH_QUORUM).get(10);
}
