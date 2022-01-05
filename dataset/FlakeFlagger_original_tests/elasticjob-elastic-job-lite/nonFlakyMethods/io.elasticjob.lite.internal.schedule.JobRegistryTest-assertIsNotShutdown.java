@Test public void assertIsNotShutdown(){
  JobScheduleController jobScheduleController=mock(JobScheduleController.class);
  CoordinatorRegistryCenter regCenter=mock(CoordinatorRegistryCenter.class);
  JobRegistry.getInstance().registerJob("test_job_for_job_not_shutdown",jobScheduleController,regCenter);
  JobRegistry.getInstance().addJobInstance("test_job_for_job_not_shutdown",new JobInstance("127.0.0.1@-@0"));
  assertFalse(JobRegistry.getInstance().isShutdown("test_job_for_job_not_shutdown"));
}
