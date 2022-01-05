@SuppressWarnings("unchecked") @Test public void assertExecuteWhenFetchDataIsNotEmptyForStreamingProcessAndSingleShardingItem(){
  setUp(true,ShardingContextsBuilder.getSingleShardingContexts());
  when(jobCaller.fetchData(0)).thenReturn(Collections.<Object>singletonList(1),Collections.emptyList());
  when(jobFacade.isEligibleForJobRunning()).thenReturn(true);
  dataflowJobExecutor.execute();
  verify(jobCaller,times(2)).fetchData(0);
  verify(jobCaller).processData(1);
}
