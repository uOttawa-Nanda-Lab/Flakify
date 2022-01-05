@Test public void assertRegisterStartUpInfo(){
  when(configService.load(false)).thenReturn(LiteJobConfiguration.newBuilder(new DataflowJobConfiguration(JobCoreConfiguration.newBuilder("test_job","0/1 * * * * ?",3).build(),TestDataflowJob.class.getCanonicalName(),false)).build());
  schedulerFacade.registerStartUpInfo(true);
  verify(listenerManager).startAllListeners();
  verify(leaderService).electLeader();
  verify(serverService).persistOnline(true);
  verify(shardingService).setReshardingFlag();
  verify(monitorService).listen();
}
