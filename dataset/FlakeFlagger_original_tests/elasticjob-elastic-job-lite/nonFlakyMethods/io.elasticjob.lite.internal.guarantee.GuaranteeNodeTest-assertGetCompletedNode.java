@Test public void assertGetCompletedNode(){
  assertThat(GuaranteeNode.getCompletedNode(1),is("guarantee/completed/1"));
}
