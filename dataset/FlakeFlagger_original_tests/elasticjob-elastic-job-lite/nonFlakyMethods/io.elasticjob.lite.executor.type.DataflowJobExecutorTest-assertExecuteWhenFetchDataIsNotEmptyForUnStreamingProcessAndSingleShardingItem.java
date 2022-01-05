@Test public void assertExecuteWhenFetchDataIsNotEmptyForUnStreamingProcessAndSingleShardingItem(){
  setUp(false,ShardingContextsBuilder.getSingleShardingContexts());
  doThrow(new IllegalStateException()).when(jobCaller).fetchData(0);
  dataflowJobExecutor.execute();
  verify(jobCaller).fetchData(0);
  verify(jobCaller,times(0)).processData(any());
}
