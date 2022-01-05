@Test public void assertGetDefaultStrategy(){
  assertThat(JobShardingStrategyFactory.getStrategy(null),instanceOf(AverageAllocationJobShardingStrategy.class));
}
