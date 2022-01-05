@Test public void assertGetItemWhenNotExecutionFailoverPath(){
  assertNull(failoverNode.getItemByExecutionFailoverPath("/test_job/sharding/0/completed"));
}
