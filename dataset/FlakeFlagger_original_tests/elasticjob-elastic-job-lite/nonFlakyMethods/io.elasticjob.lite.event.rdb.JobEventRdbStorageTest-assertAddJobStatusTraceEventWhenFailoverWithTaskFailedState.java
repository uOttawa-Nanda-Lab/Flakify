@Test public void assertAddJobStatusTraceEventWhenFailoverWithTaskFailedState() throws SQLException {
  JobStatusTraceEvent stagingJobStatusTraceEvent=new JobStatusTraceEvent("test_job","fake_failed_failover_task_id","fake_slave_id",Source.LITE_EXECUTOR,ExecutionType.FAILOVER,"0",State.TASK_STAGING,"message is empty.");
  stagingJobStatusTraceEvent.setOriginalTaskId("original_fake_failed_failover_task_id");
  storage.addJobStatusTraceEvent(stagingJobStatusTraceEvent);
  JobStatusTraceEvent failedJobStatusTraceEvent=new JobStatusTraceEvent("test_job","fake_failed_failover_task_id","fake_slave_id",Source.LITE_EXECUTOR,ExecutionType.FAILOVER,"0",State.TASK_FAILED,"message is empty.");
  storage.addJobStatusTraceEvent(failedJobStatusTraceEvent);
  List<JobStatusTraceEvent> jobStatusTraceEvents=storage.getJobStatusTraceEvents("fake_failed_failover_task_id");
  assertThat(jobStatusTraceEvents.size(),is(2));
  for (  JobStatusTraceEvent jobStatusTraceEvent : jobStatusTraceEvents) {
    assertThat(jobStatusTraceEvent.getOriginalTaskId(),is("original_fake_failed_failover_task_id"));
  }
}
