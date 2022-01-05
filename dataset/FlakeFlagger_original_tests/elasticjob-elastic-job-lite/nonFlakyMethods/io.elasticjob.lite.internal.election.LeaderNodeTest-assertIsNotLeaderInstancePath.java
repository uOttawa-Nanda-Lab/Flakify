@Test public void assertIsNotLeaderInstancePath(){
  assertFalse(leaderNode.isLeaderInstancePath("/test_job/leader/election/instance1"));
}
