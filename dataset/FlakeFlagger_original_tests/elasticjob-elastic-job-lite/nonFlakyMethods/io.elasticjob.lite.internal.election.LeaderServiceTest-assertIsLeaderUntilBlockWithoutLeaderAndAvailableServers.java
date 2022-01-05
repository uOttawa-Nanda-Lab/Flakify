@Test public void assertIsLeaderUntilBlockWithoutLeaderAndAvailableServers(){
  when(jobNodeStorage.isJobNodeExisted("leader/election/instance")).thenReturn(false);
  assertFalse(leaderService.isLeaderUntilBlock());
  verify(jobNodeStorage,times(0)).executeInLeader(eq("leader/election/latch"),ArgumentMatchers.<LeaderElectionExecutionCallback>any());
}
