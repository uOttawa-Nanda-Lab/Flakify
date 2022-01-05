@Test public void assertLeaderElectionWhenAddLeaderInstancePath(){
  electionListenerManager.new LeaderElectionJobListener().dataChanged("/test_job/leader/election/instance",Type.NODE_ADDED,"127.0.0.1");
  verify(leaderService,times(0)).electLeader();
}
