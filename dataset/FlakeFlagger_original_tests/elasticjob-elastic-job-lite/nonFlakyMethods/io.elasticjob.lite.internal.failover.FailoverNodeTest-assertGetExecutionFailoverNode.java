@Test public void assertGetExecutionFailoverNode(){
  assertThat(FailoverNode.getExecutionFailoverNode(0),is("sharding/0/failover"));
}
