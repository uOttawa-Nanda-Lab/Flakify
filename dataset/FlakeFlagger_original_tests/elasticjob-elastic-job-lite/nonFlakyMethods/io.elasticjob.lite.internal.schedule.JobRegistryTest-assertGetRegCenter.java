@Test public void assertGetRegCenter(){
  JobScheduleController jobScheduleController=mock(JobScheduleController.class);
  CoordinatorRegistryCenter regCenter=mock(CoordinatorRegistryCenter.class);
  JobRegistry.getInstance().registerJob("test_job_scheduler_for_add",jobScheduleController,regCenter);
  assertThat(JobRegistry.getInstance().getRegCenter("test_job_scheduler_for_add"),is(regCenter));
}
