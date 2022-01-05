@Test public void assertExecuteWhenExecuteFailureForMultipleShardingItems() throws IOException, NoSuchFieldException {
  assertExecuteWhenExecuteFailure(ShardingContextsBuilder.getMultipleShardingContexts());
}
