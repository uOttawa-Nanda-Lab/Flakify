@Test public void testRegisterNewNode() throws AmbariException, InvalidStateTransitionException {
  ActionManager am=new ActionManager(0,0,null,null,new ActionDBInMemoryImpl());
  Clusters fsm=clusters;
  String hostname="host1";
  fsm.addHost(hostname);
  Host hostObject=clusters.getHost(hostname);
  hostObject.setIPv4("ipv4");
  hostObject.setIPv6("ipv6");
  HeartBeatHandler handler=new HeartBeatHandler(fsm,new ActionQueue(),am,injector);
  Register reg=new Register();
  HostInfo hi=new HostInfo();
  hi.setHostName("host1");
  hi.setOS("redhat5");
  reg.setHostname(hostname);
  reg.setHardwareProfile(hi);
  RegistrationResponse response=handler.handleRegistration(reg);
  assertEquals(hostObject.getState(),HostState.HEALTHY);
  assertEquals("redhat5",hostObject.getOsType());
  assertEquals(RegistrationStatus.OK,response.getResponseStatus());
  assertEquals(0,response.getResponseId());
  assertTrue(response.getStatusCommands().isEmpty());
}
