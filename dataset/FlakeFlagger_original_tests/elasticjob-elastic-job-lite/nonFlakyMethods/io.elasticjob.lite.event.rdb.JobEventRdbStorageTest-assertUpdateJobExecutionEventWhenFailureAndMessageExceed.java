@Test public void assertUpdateJobExecutionEventWhenFailureAndMessageExceed() throws SQLException {
  JobExecutionEvent startEvent=new JobExecutionEvent("fake_task_id","test_job",JobExecutionEvent.ExecutionSource.NORMAL_TRIGGER,0);
  assertTrue(storage.addJobExecutionEvent(startEvent));
  StringBuilder failureMsg=new StringBuilder();
  for (int i=0; i < 600; i++) {
    failureMsg.append(i);
  }
  JobExecutionEvent failEvent=startEvent.executionFailure(new RuntimeException("failure" + failureMsg.toString()));
  assertTrue(storage.addJobExecutionEvent(failEvent));
  assertThat(failEvent.getFailureCause(),startsWith("java.lang.RuntimeException: failure"));
}
