@Test public void assertTriggerMisfiredWhenPreviousFireTimeIsNull(){
  when(shardingService.getLocalShardingItems()).thenReturn(Collections.singletonList(0));
  jobTriggerListener.triggerMisfired(trigger);
  verify(executionService,times(0)).setMisfire(Collections.singletonList(0));
}
