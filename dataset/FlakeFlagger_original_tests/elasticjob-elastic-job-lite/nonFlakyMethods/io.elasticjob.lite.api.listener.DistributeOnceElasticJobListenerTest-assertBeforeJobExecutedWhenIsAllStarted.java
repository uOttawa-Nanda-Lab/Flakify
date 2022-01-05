@Test public void assertBeforeJobExecutedWhenIsAllStarted(){
  when(guaranteeService.isAllStarted()).thenReturn(true);
  distributeOnceElasticJobListener.beforeJobExecuted(shardingContexts);
  verify(guaranteeService).registerStart(Sets.newHashSet(0,1));
  verify(elasticJobListenerCaller).before();
  verify(guaranteeService).clearAllStartedInfo();
}
