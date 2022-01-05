@Test public void assertGetItemByRunningItemPath(){
  assertThat(shardingNode.getItemByRunningItemPath("/test_job/sharding/0/running"),is(0));
}
