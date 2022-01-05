@Test public void assertAfterJobExecutedWhenIsAllCompletedAndNotTimeout(){
  when(guaranteeService.isAllCompleted()).thenReturn(false);
  when(timeService.getCurrentMillis()).thenReturn(0L);
  distributeOnceElasticJobListener.afterJobExecuted(shardingContexts);
  verify(guaranteeService).registerComplete(Sets.newHashSet(0,1));
  verify(guaranteeService,times(0)).clearAllCompletedInfo();
}
