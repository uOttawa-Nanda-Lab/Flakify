@Test public void assertLeaderElectionWhenServerDisableWithoutLeader(){
  electionListenerManager.new LeaderElectionJobListener().dataChanged("/test_job/servers/127.0.0.1",Type.NODE_REMOVED,ServerStatus.DISABLED.name());
  verify(leaderService,times(0)).electLeader();
}
