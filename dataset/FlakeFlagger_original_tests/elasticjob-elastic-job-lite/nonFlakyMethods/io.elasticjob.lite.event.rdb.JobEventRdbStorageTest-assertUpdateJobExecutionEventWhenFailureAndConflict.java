@Test public void assertUpdateJobExecutionEventWhenFailureAndConflict() throws SQLException {
  JobExecutionEvent startEvent=new JobExecutionEvent("fake_task_id","test_job",JobExecutionEvent.ExecutionSource.NORMAL_TRIGGER,0);
  JobExecutionEvent failureEvent=startEvent.executionFailure(new RuntimeException("failure"));
  assertTrue(storage.addJobExecutionEvent(failureEvent));
  assertThat(failureEvent.getFailureCause(),startsWith("java.lang.RuntimeException: failure"));
  assertFalse(storage.addJobExecutionEvent(startEvent));
}
