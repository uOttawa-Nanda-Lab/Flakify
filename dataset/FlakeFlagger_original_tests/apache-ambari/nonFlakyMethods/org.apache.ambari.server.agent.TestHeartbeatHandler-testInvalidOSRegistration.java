@Test public void testInvalidOSRegistration() throws AmbariException, InvalidStateTransitionException {
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
  hi.setOS("MegaOperatingSystem");
  reg.setHostname(hostname);
  reg.setHardwareProfile(hi);
  try {
    handler.handleRegistration(reg);
    fail("Expected failure for non matching os type");
  }
 catch (  AmbariException e) {
  }
}
