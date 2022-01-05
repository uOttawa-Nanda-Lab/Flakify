@Test public void assertLeaderElectionWhenServerEnableWithLeader(){
  when(leaderService.hasLeader()).thenReturn(true);
  electionListenerManager.new LeaderElectionJobListener().dataChanged("/test_job/servers/127.0.0.1",Type.NODE_UPDATED,"");
  verify(leaderService,times(0)).electLeader();
}
