@Test public void assertPostJobExecutionEvent(){
  JobExecutionEvent jobExecutionEvent=new JobExecutionEvent("fake_task_id",JOB_NAME,JobExecutionEvent.ExecutionSource.NORMAL_TRIGGER,0);
  jobEventBus.post(jobExecutionEvent);
  verify(repository,atMost(1)).addJobExecutionEvent(jobExecutionEvent);
}
