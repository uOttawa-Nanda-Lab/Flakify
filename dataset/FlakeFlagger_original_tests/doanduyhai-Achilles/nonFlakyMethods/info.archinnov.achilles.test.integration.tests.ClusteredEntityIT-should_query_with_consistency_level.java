@Test public void should_query_with_consistency_level() throws Exception {
  Long partitionKey=RandomUtils.nextLong();
  insertValues(partitionKey,1,5);
  exception.expect(InvalidQueryException.class);
  exception.expectMessage("EACH_QUORUM ConsistencyLevel is only supported for writes");
  manager.sliceQuery(ClusteredEntity.class).partitionComponents(partitionKey).fromClusterings(1,"name2").toClusterings(1,"name4").consistencyLevel(EACH_QUORUM).get();
}
