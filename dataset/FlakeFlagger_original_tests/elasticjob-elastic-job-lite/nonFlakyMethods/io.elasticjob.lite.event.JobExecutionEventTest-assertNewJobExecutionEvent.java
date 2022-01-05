@Test public void assertNewJobExecutionEvent(){
  JobExecutionEvent actual=new JobExecutionEvent("fake_task_id","test_job",JobExecutionEvent.ExecutionSource.NORMAL_TRIGGER,0);
  assertThat(actual.getJobName(),is("test_job"));
  assertThat(actual.getSource(),is(JobExecutionEvent.ExecutionSource.NORMAL_TRIGGER));
  assertThat(actual.getShardingItem(),is(0));
  assertNotNull(actual.getHostname());
  assertNotNull(actual.getStartTime());
  assertNull(actual.getCompleteTime());
  assertFalse(actual.isSuccess());
  assertThat(actual.getFailureCause(),is(""));
}
