@Test public void assertTriggerMisfiredWhenPreviousFireTimeIsNotNull(){
  when(shardingService.getLocalShardingItems()).thenReturn(Collections.singletonList(0));
  when(trigger.getPreviousFireTime()).thenReturn(new Date());
  jobTriggerListener.triggerMisfired(trigger);
  verify(executionService).setMisfire(Collections.singletonList(0));
}
