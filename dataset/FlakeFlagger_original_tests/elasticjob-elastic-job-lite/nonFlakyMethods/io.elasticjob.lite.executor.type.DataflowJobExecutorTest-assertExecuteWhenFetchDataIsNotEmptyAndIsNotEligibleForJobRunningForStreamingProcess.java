@Test public void assertExecuteWhenFetchDataIsNotEmptyAndIsNotEligibleForJobRunningForStreamingProcess(){
  setUp(true,ShardingContextsBuilder.getMultipleShardingContexts());
  when(jobFacade.isEligibleForJobRunning()).thenReturn(false);
  when(jobCaller.fetchData(0)).thenReturn(Arrays.<Object>asList(1,2));
  when(jobCaller.fetchData(1)).thenReturn(Arrays.<Object>asList(3,4));
  doThrow(new IllegalStateException()).when(jobCaller).processData(4);
  dataflowJobExecutor.execute();
  verify(jobCaller).fetchData(0);
  verify(jobCaller).fetchData(1);
  verify(jobCaller).processData(1);
  verify(jobCaller).processData(2);
  verify(jobCaller).processData(3);
  verify(jobCaller).processData(4);
}
