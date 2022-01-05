@Test(expected=JobSystemException.class) public void assertExecuteWhenExecuteFailureForSingleShardingItems() throws IOException, NoSuchFieldException {
  assertExecuteWhenExecuteFailure(ShardingContextsBuilder.getSingleShardingContexts());
}
