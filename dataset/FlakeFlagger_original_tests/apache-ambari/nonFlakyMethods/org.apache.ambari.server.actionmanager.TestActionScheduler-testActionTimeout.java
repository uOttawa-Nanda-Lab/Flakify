/** 
 * Test whether scheduler times out an action
 */
@Test public void testActionTimeout() throws Exception {
  ActionQueue aq=new ActionQueue();
  Clusters fsm=mock(Clusters.class);
  Cluster oneClusterMock=mock(Cluster.class);
  Service serviceObj=mock(Service.class);
  ServiceComponent scomp=mock(ServiceComponent.class);
  ServiceComponentHost sch=mock(ServiceComponentHost.class);
  when(fsm.getCluster(anyString())).thenReturn(oneClusterMock);
  when(oneClusterMock.getService(anyString())).thenReturn(serviceObj);
  when(serviceObj.getServiceComponent(anyString())).thenReturn(scomp);
  when(scomp.getServiceComponentHost(anyString())).thenReturn(sch);
  when(serviceObj.getCluster()).thenReturn(oneClusterMock);
  ActionDBAccessor db=new ActionDBInMemoryImpl();
  String hostname="ahost.ambari.apache.org";
  List<Stage> stages=new ArrayList<Stage>();
  Stage s=StageUtils.getATestStage(1,977,hostname);
  stages.add(s);
  db.persistActions(stages);
  ActionScheduler scheduler=new ActionScheduler(100,50,db,aq,fsm,3);
  scheduler.setTaskTimeoutAdjustment(false);
  scheduler.start();
  while (!stages.get(0).getHostRoleStatus(hostname,"NAMENODE").equals(HostRoleStatus.TIMEDOUT)) {
    Thread.sleep(100);
  }
  assertEquals(stages.get(0).getHostRoleStatus(hostname,"NAMENODE"),HostRoleStatus.TIMEDOUT);
}
