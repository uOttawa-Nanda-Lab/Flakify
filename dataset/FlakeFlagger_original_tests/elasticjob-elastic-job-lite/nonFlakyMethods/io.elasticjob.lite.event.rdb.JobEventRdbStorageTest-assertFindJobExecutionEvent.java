@Test public void assertFindJobExecutionEvent() throws SQLException {
  storage.addJobExecutionEvent(new JobExecutionEvent("fake_task_id","test_job",JobExecutionEvent.ExecutionSource.NORMAL_TRIGGER,0));
}
