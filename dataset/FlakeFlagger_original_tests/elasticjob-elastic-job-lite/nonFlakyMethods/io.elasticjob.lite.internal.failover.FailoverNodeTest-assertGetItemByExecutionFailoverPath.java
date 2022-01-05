@Test public void assertGetItemByExecutionFailoverPath(){
  assertThat(failoverNode.getItemByExecutionFailoverPath("/test_job/sharding/0/failover"),is(0));
}
