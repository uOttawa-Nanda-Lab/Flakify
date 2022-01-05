@Test(expected=JobConfigurationException.class) public void assertGetStrategyFailureWhenStrategyClassInvalid(){
  JobShardingStrategyFactory.getStrategy(InvalidJobShardingStrategy.class.getName());
}
