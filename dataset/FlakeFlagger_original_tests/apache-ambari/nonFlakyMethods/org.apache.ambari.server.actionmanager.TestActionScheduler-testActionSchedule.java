/** 
 * This test sends a new action to the action scheduler and verifies that the action shows up in the action queue.
 */
@Test public void testActionSchedule() throws Exception {
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
  ActionDBAccessor db=mock(ActionDBAccessorImpl.class);
  List<Stage> stages=new ArrayList<Stage>();
  String hostname="ahost.ambari.apache.org";
  Stage s=StageUtils.getATestStage(1,977,hostname);
  stages.add(s);
  when(db.getStagesInProgress()).thenReturn(stages);
  ActionScheduler scheduler=new ActionScheduler(100,100,db,aq,fsm,10000);
  scheduler.setTaskTimeoutAdjustment(false);
  scheduler.start();
  List<AgentCommand> ac=waitForQueueSize(hostname,aq,1);
  assertTrue(ac.get(0) instanceof ExecutionCommand);
  assertEquals("1-977",((ExecutionCommand)(ac.get(0))).getCommandId());
  ac=waitForQueueSize(hostname,aq,1);
  assertTrue(ac.get(0) instanceof ExecutionCommand);
  assertEquals("1-977",((ExecutionCommand)(ac.get(0))).getCommandId());
  s.setHostRoleStatus(hostname,"NAMENODE",HostRoleStatus.COMPLETED);
  ac=aq.dequeueAll(hostname);
  ac=waitForQueueSize(hostname,aq,0);
  scheduler.stop();
}
