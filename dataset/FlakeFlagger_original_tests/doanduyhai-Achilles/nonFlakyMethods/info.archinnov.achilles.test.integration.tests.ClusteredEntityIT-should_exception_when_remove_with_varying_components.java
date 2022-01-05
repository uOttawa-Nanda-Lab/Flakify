@Test public void should_exception_when_remove_with_varying_components() throws Exception {
  long partitionKey=RandomUtils.nextLong();
  insertValues(partitionKey,1,5);
  exception.expect(AchillesException.class);
  exception.expectMessage("CQL does not support slice delete with varying compound components");
  manager.sliceQuery(ClusteredEntity.class).partitionComponents(partitionKey).fromClusterings(1).toClusterings(1,"name2").ordering(DESCENDING).limit(2).remove();
}
