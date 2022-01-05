@Test public void assertPostJobStatusTraceEvent(){
  JobStatusTraceEvent jobStatusTraceEvent=new JobStatusTraceEvent(JOB_NAME,"fake_task_id","fake_slave_id",Source.LITE_EXECUTOR,ExecutionType.READY,"0",State.TASK_RUNNING,"message is empty.");
  jobEventBus.post(jobStatusTraceEvent);
  verify(repository,atMost(1)).addJobStatusTraceEvent(jobStatusTraceEvent);
}
