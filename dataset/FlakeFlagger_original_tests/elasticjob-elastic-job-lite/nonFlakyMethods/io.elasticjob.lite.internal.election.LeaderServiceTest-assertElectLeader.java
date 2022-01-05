@Test public void assertElectLeader(){
  leaderService.electLeader();
  verify(jobNodeStorage).executeInLeader(eq("leader/election/latch"),ArgumentMatchers.<LeaderElectionExecutionCallback>any());
}
