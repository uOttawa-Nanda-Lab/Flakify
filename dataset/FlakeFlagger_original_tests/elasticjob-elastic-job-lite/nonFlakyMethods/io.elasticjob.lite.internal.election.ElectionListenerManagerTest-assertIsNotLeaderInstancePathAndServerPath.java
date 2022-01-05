@Test public void assertIsNotLeaderInstancePathAndServerPath(){
  electionListenerManager.new LeaderElectionJobListener().dataChanged("/test_job/leader/election/other",Type.NODE_REMOVED,"127.0.0.1");
  verify(leaderService,times(0)).electLeader();
}
