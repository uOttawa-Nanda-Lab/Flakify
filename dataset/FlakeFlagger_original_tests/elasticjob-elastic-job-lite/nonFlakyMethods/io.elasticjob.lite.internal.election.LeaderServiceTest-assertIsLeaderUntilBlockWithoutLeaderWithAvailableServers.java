@Test public void assertIsLeaderUntilBlockWithoutLeaderWithAvailableServers(){
  when(jobNodeStorage.isJobNodeExisted("leader/election/instance")).thenReturn(false,true);
  assertFalse(leaderService.isLeaderUntilBlock());
  verify(jobNodeStorage,times(0)).executeInLeader(eq("leader/election/latch"),ArgumentMatchers.<LeaderElectionExecutionCallback>any());
}
