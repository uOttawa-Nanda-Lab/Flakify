@Test public void assertUpdateJobExecutionEventWhenFailure() throws SQLException {
  JobExecutionEvent startEvent=new JobExecutionEvent("fake_task_id","test_job",JobExecutionEvent.ExecutionSource.NORMAL_TRIGGER,0);
  assertTrue(storage.addJobExecutionEvent(startEvent));
  JobExecutionEvent failureEvent=startEvent.executionFailure(new RuntimeException("failure"));
  assertTrue(storage.addJobExecutionEvent(failureEvent));
  assertThat(failureEvent.getFailureCause(),startsWith("java.lang.RuntimeException: failure"));
  assertTrue(null != failureEvent.getCompleteTime());
}
