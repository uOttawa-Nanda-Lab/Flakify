@Test public void assertGetLocalShardingItemsWithDisabledServer(){
  JobRegistry.getInstance().registerJob("test_job",jobScheduleController,regCenter);
  assertThat(shardingService.getLocalShardingItems(),is(Collections.<Integer>emptyList()));
  JobRegistry.getInstance().shutdown("test_job");
}
