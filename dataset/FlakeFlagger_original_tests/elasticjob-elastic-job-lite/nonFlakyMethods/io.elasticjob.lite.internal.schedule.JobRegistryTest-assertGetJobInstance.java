@Test public void assertGetJobInstance(){
  JobRegistry.getInstance().addJobInstance("exist_job_instance",new JobInstance("127.0.0.1@-@0"));
  assertThat(JobRegistry.getInstance().getJobInstance("exist_job_instance"),is(new JobInstance("127.0.0.1@-@0")));
}
