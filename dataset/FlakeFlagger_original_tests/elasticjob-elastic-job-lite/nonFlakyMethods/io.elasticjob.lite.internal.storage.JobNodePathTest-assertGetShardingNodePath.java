@Test public void assertGetShardingNodePath(){
  assertThat(jobNodePath.getShardingNodePath(),is("/test_job/sharding"));
}
