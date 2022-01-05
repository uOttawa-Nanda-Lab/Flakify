@Test public void assertIsShutdownForJobInstanceNull(){
  JobScheduleController jobScheduleController=mock(JobScheduleController.class);
  CoordinatorRegistryCenter regCenter=mock(CoordinatorRegistryCenter.class);
  JobRegistry.getInstance().registerJob("test_job_for_job_instance_null",jobScheduleController,regCenter);
  assertTrue(JobRegistry.getInstance().isShutdown("test_job_for_job_instance_null"));
}
