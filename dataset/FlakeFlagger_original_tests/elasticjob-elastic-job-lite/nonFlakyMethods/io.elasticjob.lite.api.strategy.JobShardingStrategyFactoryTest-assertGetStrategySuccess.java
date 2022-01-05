@Test public void assertGetStrategySuccess(){
  assertThat(JobShardingStrategyFactory.getStrategy(AverageAllocationJobShardingStrategy.class.getName()),instanceOf(AverageAllocationJobShardingStrategy.class));
}
