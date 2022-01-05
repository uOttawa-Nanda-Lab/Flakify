@Test public void assertAddJobStatusTraceEventWhenFailoverWithTaskStagingState() throws SQLException {
  JobStatusTraceEvent jobStatusTraceEvent=new JobStatusTraceEvent("test_job","fake_failover_task_id","fake_slave_id",Source.LITE_EXECUTOR,ExecutionType.FAILOVER,"0",State.TASK_STAGING,"message is empty.");
  jobStatusTraceEvent.setOriginalTaskId("original_fake_failover_task_id");
  assertThat(storage.getJobStatusTraceEvents("fake_failover_task_id").size(),is(0));
  storage.addJobStatusTraceEvent(jobStatusTraceEvent);
  assertThat(storage.getJobStatusTraceEvents("fake_failover_task_id").size(),is(1));
}
