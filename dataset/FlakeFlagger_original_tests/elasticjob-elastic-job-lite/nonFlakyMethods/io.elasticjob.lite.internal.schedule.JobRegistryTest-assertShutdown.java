@Test public void assertShutdown(){
  JobScheduleController jobScheduleController=mock(JobScheduleController.class);
  CoordinatorRegistryCenter regCenter=mock(CoordinatorRegistryCenter.class);
  JobRegistry.getInstance().registerJob("test_job_for_shutdown",jobScheduleController,regCenter);
  JobRegistry.getInstance().shutdown("test_job_for_shutdown");
  verify(jobScheduleController).shutdown();
  verify(regCenter).evictCacheData("/test_job_for_shutdown");
}
