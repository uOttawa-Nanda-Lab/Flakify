@Test public void assertAfterJobExecutedWhenIsAllCompleted(){
  when(guaranteeService.isAllCompleted()).thenReturn(true);
  distributeOnceElasticJobListener.afterJobExecuted(shardingContexts);
  verify(guaranteeService).registerComplete(Sets.newHashSet(0,1));
  verify(elasticJobListenerCaller).after();
  verify(guaranteeService).clearAllCompletedInfo();
}
