@Test public void assertElectLeaderExecutionCallbackWithoutLeader(){
  leaderService.new LeaderElectionExecutionCallback().execute();
  verify(jobNodeStorage).fillEphemeralJobNode("leader/election/instance","127.0.0.1@-@0");
}
