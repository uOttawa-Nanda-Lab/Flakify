@Test public void assertIsJobRunningIfNull(){
  assertFalse(JobRegistry.getInstance().isJobRunning("null_job_instance"));
}
