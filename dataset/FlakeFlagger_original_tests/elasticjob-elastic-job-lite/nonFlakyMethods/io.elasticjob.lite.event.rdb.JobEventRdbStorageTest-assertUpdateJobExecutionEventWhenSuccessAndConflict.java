@Test public void assertUpdateJobExecutionEventWhenSuccessAndConflict() throws SQLException {
  JobExecutionEvent startEvent=new JobExecutionEvent("fake_task_id","test_job",JobExecutionEvent.ExecutionSource.NORMAL_TRIGGER,0);
  JobExecutionEvent successEvent=startEvent.executionSuccess();
  assertTrue(storage.addJobExecutionEvent(successEvent));
  assertFalse(storage.addJobExecutionEvent(startEvent));
}
