@Test public void assertGetRunningNode(){
  assertThat(ShardingNode.getRunningNode(0),is("sharding/0/running"));
}
