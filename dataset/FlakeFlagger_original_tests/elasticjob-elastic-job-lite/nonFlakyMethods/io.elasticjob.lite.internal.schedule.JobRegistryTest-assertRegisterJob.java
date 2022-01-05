@Test public void assertRegisterJob(){
  JobScheduleController jobScheduleController=mock(JobScheduleController.class);
  CoordinatorRegistryCenter regCenter=mock(CoordinatorRegistryCenter.class);
  JobRegistry.getInstance().registerJob("test_job_scheduler_for_add",jobScheduleController,regCenter);
  assertThat(JobRegistry.getInstance().getJobScheduleController("test_job_scheduler_for_add"),is(jobScheduleController));
}
