@Test public void testHeartbeatLoss() throws AmbariException, InterruptedException, InvalidStateTransitionException {
  Clusters fsm=injector.getInstance(Clusters.class);
  String hostname="host1";
  fsm.addHost(hostname);
  ActionQueue aq=new ActionQueue();
  ActionManager am=mock(ActionManager.class);
  HeartbeatMonitor hm=new HeartbeatMonitor(fsm,aq,am,10);
  HeartBeatHandler handler=new HeartBeatHandler(fsm,aq,am,injector);
  Register reg=new Register();
  reg.setHostname(hostname);
  reg.setResponseId(12);
  reg.setTimestamp(System.currentTimeMillis() - 300);
  HostInfo hi=new HostInfo();
  hi.setOS("Centos5");
  reg.setHardwareProfile(hi);
  handler.handleRegistration(reg);
  HeartBeat hb=new HeartBeat();
  hb.setHostname(hostname);
  hb.setNodeStatus(new HostStatus(HostStatus.Status.HEALTHY,"cool"));
  hb.setTimestamp(System.currentTimeMillis());
  hb.setResponseId(12);
  handler.handleHeartBeat(hb);
  hm.start();
  aq.enqueue(hostname,new ExecutionCommand());
  while (aq.size(hostname) != 0) {
    Thread.sleep(1);
  }
  assertEquals(fsm.getHost(hostname).getState(),HostState.HEARTBEAT_LOST);
}
