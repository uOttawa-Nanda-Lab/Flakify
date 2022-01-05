@Test public void assertExecuteWhenFetchDataIsNullAndEmpty(){
  setUp(true,ShardingContextsBuilder.getMultipleShardingContexts());
  when(jobCaller.fetchData(0)).thenReturn(null);
  when(jobCaller.fetchData(1)).thenReturn(Collections.emptyList());
  dataflowJobExecutor.execute();
  verify(jobCaller).fetchData(0);
  verify(jobCaller).fetchData(1);
  verify(jobCaller,times(0)).processData(any());
}
