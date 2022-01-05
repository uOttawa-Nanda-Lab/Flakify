@Test public void assertIsShutdownForJobSchedulerNull(){
  assertTrue(JobRegistry.getInstance().isShutdown("test_job_for_job_scheduler_null"));
}
