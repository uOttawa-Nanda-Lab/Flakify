@Test public void should_query_with_consistency_level() throws Exception {
  Long id=RandomUtils.nextLong();
  insertValues(id,5);
  exception.expect(InvalidQueryException.class);
  exception.expectMessage("EACH_QUORUM ConsistencyLevel is only supported for writes");
  manager.sliceQuery(ClusteredEntityWithCompositePartitionKey.class).partitionComponents(id,"type").fromClusterings(12).toClusterings(14).consistencyLevel(EACH_QUORUM).get();
}
