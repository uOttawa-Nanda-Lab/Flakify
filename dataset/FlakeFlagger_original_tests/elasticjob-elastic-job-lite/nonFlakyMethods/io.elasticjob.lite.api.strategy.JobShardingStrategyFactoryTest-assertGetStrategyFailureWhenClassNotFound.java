@Test(expected=JobConfigurationException.class) public void assertGetStrategyFailureWhenClassNotFound(){
  JobShardingStrategyFactory.getStrategy("NotClass");
}
