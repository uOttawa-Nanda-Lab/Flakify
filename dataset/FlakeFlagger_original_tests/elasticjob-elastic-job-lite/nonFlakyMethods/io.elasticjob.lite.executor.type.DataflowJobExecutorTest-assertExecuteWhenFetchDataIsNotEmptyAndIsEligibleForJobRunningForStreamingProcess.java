@SuppressWarnings("unchecked") @Test public void assertExecuteWhenFetchDataIsNotEmptyAndIsEligibleForJobRunningForStreamingProcess(){
  setUp(true,ShardingContextsBuilder.getMultipleShardingContexts());
  when(jobFacade.isEligibleForJobRunning()).thenReturn(true);
  when(jobCaller.fetchData(0)).thenReturn(Arrays.<Object>asList(1,2),Collections.emptyList());
  when(jobCaller.fetchData(1)).thenReturn(Arrays.<Object>asList(3,4),Collections.emptyList());
  doThrow(new IllegalStateException()).when(jobCaller).processData(4);
  dataflowJobExecutor.execute();
  verify(jobCaller,times(2)).fetchData(0);
  verify(jobCaller,times(1)).fetchData(1);
  verify(jobCaller).processData(1);
  verify(jobCaller).processData(2);
  verify(jobCaller).processData(3);
  verify(jobCaller).processData(4);
}
