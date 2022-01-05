@Test(expected=JobConfigurationException.class) public void assertNewWhenPairFormatInvalid(){
  new ShardingItemParameters("xxx-xxx");
}
