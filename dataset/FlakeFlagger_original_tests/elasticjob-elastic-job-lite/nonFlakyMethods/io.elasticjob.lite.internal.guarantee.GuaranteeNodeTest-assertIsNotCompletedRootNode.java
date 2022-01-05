@Test public void assertIsNotCompletedRootNode(){
  assertFalse(guaranteeNode.isCompletedRootNode("/otherJob/guarantee/completed"));
}
