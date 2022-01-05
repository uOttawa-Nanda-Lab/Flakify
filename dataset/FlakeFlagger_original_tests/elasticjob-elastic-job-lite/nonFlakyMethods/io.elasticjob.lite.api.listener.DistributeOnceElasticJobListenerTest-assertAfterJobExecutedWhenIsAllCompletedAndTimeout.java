@Test(expected=JobSystemException.class) public void assertAfterJobExecutedWhenIsAllCompletedAndTimeout(){
  when(guaranteeService.isAllCompleted()).thenReturn(false);
  when(timeService.getCurrentMillis()).thenReturn(0L,2L);
  distributeOnceElasticJobListener.afterJobExecuted(shardingContexts);
  verify(guaranteeService).registerComplete(Arrays.asList(0,1));
  verify(guaranteeService,times(0)).clearAllCompletedInfo();
}
