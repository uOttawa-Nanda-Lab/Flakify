@Test public void assertIsNeedSharding(){
  when(jobNodeStorage.isJobNodeExisted("leader/sharding/necessary")).thenReturn(true);
  assertTrue(shardingService.isNeedSharding());
}
