@Test public void assertBeforeJobExecutedWhenIsNotAllStartedAndNotTimeout(){
  when(guaranteeService.isAllStarted()).thenReturn(false);
  when(timeService.getCurrentMillis()).thenReturn(0L);
  distributeOnceElasticJobListener.beforeJobExecuted(shardingContexts);
  verify(guaranteeService).registerStart(Sets.newHashSet(0,1));
  verify(guaranteeService,times(0)).clearAllStartedInfo();
}
