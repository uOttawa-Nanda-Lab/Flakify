@Test public void assertGetCurrentShardingTotalCountIfNull(){
  assertThat(JobRegistry.getInstance().getCurrentShardingTotalCount("exist_job_instance"),is(0));
}
