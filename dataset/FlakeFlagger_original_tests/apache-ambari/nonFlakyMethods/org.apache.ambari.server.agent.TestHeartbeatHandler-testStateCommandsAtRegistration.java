@Test public void testStateCommandsAtRegistration() throws AmbariException, InvalidStateTransitionException {
  List<StatusCommand> dummyCmds=new ArrayList<StatusCommand>();
  StatusCommand statusCmd1=new StatusCommand();
  statusCmd1.setClusterName("Cluster");
  statusCmd1.setServiceName("HDFS");
  dummyCmds.add(statusCmd1);
  HeartbeatMonitor hm=mock(HeartbeatMonitor.class);
  when(hm.generateStatusCommands(anyString())).thenReturn(dummyCmds);
  ActionManager am=new ActionManager(0,0,null,null,new ActionDBInMemoryImpl());
  Clusters fsm=clusters;
  String hostname="host1";
  ActionQueue actionQueue=new ActionQueue();
  HeartBeatHandler handler=new HeartBeatHandler(fsm,actionQueue,am,injector);
  handler.setHeartbeatMonitor(hm);
  clusters.addHost(hostname);
  Host hostObject=clusters.getHost(hostname);
  hostObject.setIPv4("ipv4");
  hostObject.setIPv6("ipv6");
  Register reg=new Register();
  HostInfo hi=new HostInfo();
  hi.setHostName("host1");
  hi.setOS("redhat5");
  reg.setHostname(hostname);
  reg.setHardwareProfile(hi);
  RegistrationResponse registrationResponse=handler.handleRegistration(reg);
  registrationResponse.getStatusCommands();
  assertTrue(registrationResponse.getStatusCommands().size() == 1);
  assertTrue(registrationResponse.getStatusCommands().get(0).equals(statusCmd1));
}
