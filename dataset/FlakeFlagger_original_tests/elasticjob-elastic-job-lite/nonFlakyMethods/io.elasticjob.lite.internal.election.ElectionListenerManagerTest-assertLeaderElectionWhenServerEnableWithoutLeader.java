@Test public void assertLeaderElectionWhenServerEnableWithoutLeader(){
  JobRegistry.getInstance().registerJob("test_job",jobScheduleController,regCenter);
  electionListenerManager.new LeaderElectionJobListener().dataChanged("/test_job/servers/127.0.0.1",Type.NODE_UPDATED,"");
  verify(leaderService).electLeader();
  JobRegistry.getInstance().shutdown("test_job");
}
