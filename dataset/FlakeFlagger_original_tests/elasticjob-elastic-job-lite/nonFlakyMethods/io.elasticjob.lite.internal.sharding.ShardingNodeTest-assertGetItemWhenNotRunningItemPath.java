@Test public void assertGetItemWhenNotRunningItemPath(){
  assertNull(shardingNode.getItemByRunningItemPath("/test_job/sharding/0/completed"));
}
