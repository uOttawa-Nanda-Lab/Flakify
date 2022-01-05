@Test public void assertIsJobRunningIfNotNull(){
  JobRegistry.getInstance().setJobRunning("exist_job_instance",true);
  assertTrue(JobRegistry.getInstance().isJobRunning("exist_job_instance"));
}
