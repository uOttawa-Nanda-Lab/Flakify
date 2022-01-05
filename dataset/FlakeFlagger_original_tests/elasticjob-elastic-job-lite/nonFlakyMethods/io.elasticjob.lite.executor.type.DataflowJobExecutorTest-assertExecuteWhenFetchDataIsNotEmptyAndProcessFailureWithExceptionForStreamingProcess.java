@SuppressWarnings("unchecked") @Test public void assertExecuteWhenFetchDataIsNotEmptyAndProcessFailureWithExceptionForStreamingProcess(){
  setUp(true,ShardingContextsBuilder.getMultipleShardingContexts());
  when(jobCaller.fetchData(0)).thenReturn(Collections.<Object>singletonList(1),Collections.emptyList());
  when(jobCaller.fetchData(1)).thenReturn(Arrays.<Object>asList(2,3),Collections.emptyList());
  when(jobFacade.isEligibleForJobRunning()).thenReturn(true);
  doThrow(new IllegalStateException()).when(jobCaller).processData(2);
  dataflowJobExecutor.execute();
  verify(jobCaller,times(2)).fetchData(0);
  verify(jobCaller,times(1)).fetchData(1);
  verify(jobCaller).processData(1);
  verify(jobCaller).processData(2);
  verify(jobCaller,times(0)).processData(3);
}
