@Test public void assertGetShardingNodePathWihItemAndNode(){
  assertThat(jobNodePath.getShardingNodePath("0","running"),is("/test_job/sharding/0/running"));
}
