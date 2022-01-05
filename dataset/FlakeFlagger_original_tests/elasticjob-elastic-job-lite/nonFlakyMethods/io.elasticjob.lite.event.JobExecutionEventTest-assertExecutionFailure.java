@Test public void assertExecutionFailure(){
  JobExecutionEvent startEvent=new JobExecutionEvent("fake_task_id","test_job",JobExecutionEvent.ExecutionSource.NORMAL_TRIGGER,0);
  JobExecutionEvent failureEvent=startEvent.executionFailure(new RuntimeException("failure"));
  assertNotNull(failureEvent.getCompleteTime());
  assertFalse(failureEvent.isSuccess());
  assertThat(failureEvent.getFailureCause(),startsWith("java.lang.RuntimeException: failure"));
}
