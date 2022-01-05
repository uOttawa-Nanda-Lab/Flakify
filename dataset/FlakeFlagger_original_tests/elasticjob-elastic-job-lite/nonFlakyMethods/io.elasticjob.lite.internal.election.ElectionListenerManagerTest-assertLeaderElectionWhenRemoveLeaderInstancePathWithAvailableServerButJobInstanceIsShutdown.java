@Test public void assertLeaderElectionWhenRemoveLeaderInstancePathWithAvailableServerButJobInstanceIsShutdown(){
  when(serverService.isAvailableServer("127.0.0.1")).thenReturn(true);
  electionListenerManager.new LeaderElectionJobListener().dataChanged("/test_job/leader/election/instance",Type.NODE_REMOVED,"127.0.0.1");
  verify(leaderService,times(0)).electLeader();
}
