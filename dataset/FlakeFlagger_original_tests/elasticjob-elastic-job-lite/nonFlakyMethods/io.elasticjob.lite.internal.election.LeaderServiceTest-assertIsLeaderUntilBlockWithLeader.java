@Test public void assertIsLeaderUntilBlockWithLeader(){
  JobRegistry.getInstance().registerJob("test_job",jobScheduleController,regCenter);
  when(jobNodeStorage.isJobNodeExisted("leader/election/instance")).thenReturn(true);
  when(jobNodeStorage.getJobNodeData("leader/election/instance")).thenReturn("127.0.0.1@-@0");
  assertTrue(leaderService.isLeaderUntilBlock());
  verify(jobNodeStorage,times(0)).executeInLeader(eq("leader/election/latch"),ArgumentMatchers.<LeaderElectionExecutionCallback>any());
  JobRegistry.getInstance().shutdown("test_job");
}
