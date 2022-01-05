@Test(expected=JobConfigurationException.class) public void assertGetStrategyFailureWhenNotStrategyClass(){
  JobShardingStrategyFactory.getStrategy(Object.class.getName());
}
