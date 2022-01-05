@Test public void assertAddJobStatusTraceEvent() throws SQLException {
  assertTrue(storage.addJobStatusTraceEvent(new JobStatusTraceEvent("test_job","fake_task_id","fake_slave_id",Source.LITE_EXECUTOR,ExecutionType.READY,"0",State.TASK_RUNNING,"message is empty.")));
}
