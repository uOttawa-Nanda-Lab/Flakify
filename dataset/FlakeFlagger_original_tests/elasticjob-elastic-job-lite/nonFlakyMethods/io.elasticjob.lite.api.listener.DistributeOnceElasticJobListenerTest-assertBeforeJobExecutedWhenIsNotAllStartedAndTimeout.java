@Test(expected=JobSystemException.class) public void assertBeforeJobExecutedWhenIsNotAllStartedAndTimeout(){
  when(guaranteeService.isAllStarted()).thenReturn(false);
  when(timeService.getCurrentMillis()).thenReturn(0L,2L);
  distributeOnceElasticJobListener.beforeJobExecuted(shardingContexts);
  verify(guaranteeService).registerStart(Arrays.asList(0,1));
  verify(guaranteeService,times(0)).clearAllStartedInfo();
}
