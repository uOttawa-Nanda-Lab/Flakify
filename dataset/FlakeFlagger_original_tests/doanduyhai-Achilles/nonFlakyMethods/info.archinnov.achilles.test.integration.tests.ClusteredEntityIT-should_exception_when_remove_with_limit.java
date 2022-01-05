@Test public void should_exception_when_remove_with_limit() throws Exception {
  long partitionKey=RandomUtils.nextLong();
  insertValues(partitionKey,1,5);
  exception.expect(AchillesException.class);
  exception.expectMessage("CQL slice delete does not support LIMIT");
  manager.sliceQuery(ClusteredEntity.class).partitionComponents(partitionKey).remove(3);
}
