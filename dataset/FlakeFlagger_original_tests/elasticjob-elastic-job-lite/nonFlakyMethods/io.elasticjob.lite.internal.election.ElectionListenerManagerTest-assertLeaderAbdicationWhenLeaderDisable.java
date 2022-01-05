@Test public void assertLeaderAbdicationWhenLeaderDisable(){
  when(leaderService.isLeader()).thenReturn(true);
  electionListenerManager.new LeaderAbdicationJobListener().dataChanged("/test_job/servers/127.0.0.1",Type.NODE_UPDATED,ServerStatus.DISABLED.name());
  verify(leaderService).removeLeader();
}
