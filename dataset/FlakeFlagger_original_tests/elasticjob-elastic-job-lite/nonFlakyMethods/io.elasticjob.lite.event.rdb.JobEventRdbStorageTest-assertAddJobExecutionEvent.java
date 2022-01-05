@Test public void assertAddJobExecutionEvent() throws SQLException {
  assertTrue(storage.addJobExecutionEvent(new JobExecutionEvent("fake_task_id","test_job",JobExecutionEvent.ExecutionSource.NORMAL_TRIGGER,0)));
}
