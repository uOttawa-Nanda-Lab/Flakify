@Test public void assertIsLeaderUntilBlockWhenHasLeader(){
  JobRegistry.getInstance().registerJob("test_job",jobScheduleController,regCenter);
  when(jobNodeStorage.isJobNodeExisted("leader/election/instance")).thenReturn(false,true);
  when(serverService.hasAvailableServers()).thenReturn(true);
  when(serverService.isAvailableServer("127.0.0.1")).thenReturn(true);
  when(jobNodeStorage.getJobNodeData("leader/election/instance")).thenReturn("127.0.0.1@-@0");
  assertTrue(leaderService.isLeaderUntilBlock());
  verify(jobNodeStorage).executeInLeader(eq("leader/election/latch"),ArgumentMatchers.<LeaderElectionExecutionCallback>any());
  JobRegistry.getInstance().shutdown("test_job");
}
