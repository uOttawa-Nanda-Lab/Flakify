@Test public void assertIsLeader(){
  JobRegistry.getInstance().registerJob("test_job",jobScheduleController,regCenter);
  when(jobNodeStorage.getJobNodeData("leader/election/instance")).thenReturn("127.0.0.1@-@0");
  assertTrue(leaderService.isLeader());
  JobRegistry.getInstance().shutdown("test_job");
}
