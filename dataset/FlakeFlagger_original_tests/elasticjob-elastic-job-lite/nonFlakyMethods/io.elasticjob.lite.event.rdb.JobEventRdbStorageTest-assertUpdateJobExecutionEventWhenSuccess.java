@Test public void assertUpdateJobExecutionEventWhenSuccess() throws SQLException {
  JobExecutionEvent startEvent=new JobExecutionEvent("fake_task_id","test_job",JobExecutionEvent.ExecutionSource.NORMAL_TRIGGER,0);
  assertTrue(storage.addJobExecutionEvent(startEvent));
  JobExecutionEvent successEvent=startEvent.executionSuccess();
  assertTrue(storage.addJobExecutionEvent(successEvent));
}
