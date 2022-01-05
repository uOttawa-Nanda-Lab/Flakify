@Test public void assertElectLeaderExecutionCallbackWithLeader(){
  when(jobNodeStorage.isJobNodeExisted("leader/election/instance")).thenReturn(true);
  leaderService.new LeaderElectionExecutionCallback().execute();
  verify(jobNodeStorage,times(0)).fillEphemeralJobNode("leader/election/instance","127.0.0.1@-@0");
}
