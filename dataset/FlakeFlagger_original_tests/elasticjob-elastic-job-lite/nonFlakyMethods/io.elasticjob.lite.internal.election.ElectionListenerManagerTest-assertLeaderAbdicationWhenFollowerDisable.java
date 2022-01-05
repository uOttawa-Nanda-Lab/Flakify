@Test public void assertLeaderAbdicationWhenFollowerDisable(){
  electionListenerManager.new LeaderAbdicationJobListener().dataChanged("/test_job/servers/127.0.0.1",Type.NODE_UPDATED,ServerStatus.DISABLED.name());
  verify(leaderService,times(0)).removeLeader();
}
