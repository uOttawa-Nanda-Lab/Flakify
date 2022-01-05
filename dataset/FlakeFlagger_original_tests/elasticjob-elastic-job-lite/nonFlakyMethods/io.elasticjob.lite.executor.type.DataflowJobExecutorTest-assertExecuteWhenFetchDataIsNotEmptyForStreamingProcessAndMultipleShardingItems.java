@SuppressWarnings("unchecked") @Test public void assertExecuteWhenFetchDataIsNotEmptyForStreamingProcessAndMultipleShardingItems(){
  setUp(true,ShardingContextsBuilder.getMultipleShardingContexts());
  when(jobCaller.fetchData(0)).thenReturn(Collections.<Object>singletonList(1),Collections.emptyList());
  when(jobCaller.fetchData(1)).thenReturn(Collections.<Object>singletonList(2),Collections.emptyList());
  when(jobFacade.isEligibleForJobRunning()).thenReturn(true);
  dataflowJobExecutor.execute();
  verify(jobCaller,times(2)).fetchData(0);
  verify(jobCaller,times(2)).fetchData(1);
  verify(jobCaller).processData(1);
  verify(jobCaller).processData(2);
}
