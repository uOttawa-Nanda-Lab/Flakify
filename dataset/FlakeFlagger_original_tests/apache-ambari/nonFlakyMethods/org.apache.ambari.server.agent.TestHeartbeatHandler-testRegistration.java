@Test public void testRegistration() throws AmbariException, InvalidStateTransitionException {
  ActionManager am=new ActionManager(0,0,null,null,new ActionDBInMemoryImpl());
  Clusters fsm=clusters;
  String hostname="host1";
  HeartBeatHandler handler=new HeartBeatHandler(fsm,new ActionQueue(),am,injector);
  clusters.addHost(hostname);
  Host hostObject=clusters.getHost(hostname);
  hostObject.setIPv4("ipv4");
  hostObject.setIPv6("ipv6");
  Register reg=new Register();
  HostInfo hi=new HostInfo();
  hi.setHostName("host1");
  hi.setOS("centos5");
  reg.setHostname(hostname);
  reg.setHardwareProfile(hi);
  handler.handleRegistration(reg);
  assertEquals(hostObject.getState(),HostState.HEALTHY);
  assertEquals("centos5",hostObject.getOsType());
  assertTrue(hostObject.getLastRegistrationTime() != 0);
  assertEquals(hostObject.getLastHeartbeatTime(),hostObject.getLastRegistrationTime());
}
