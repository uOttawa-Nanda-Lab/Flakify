@Test(expected=JobConfigurationException.class) public void assertNewWhenItemIsNotNumber(){
  new ShardingItemParameters("xxx=xxx");
}
