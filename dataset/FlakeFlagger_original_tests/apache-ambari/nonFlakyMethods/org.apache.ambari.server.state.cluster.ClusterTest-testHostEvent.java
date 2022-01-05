@Test public void testHostEvent() throws AmbariException, InvalidStateTransitionException {
  HostInfo hostInfo=new HostInfo();
  hostInfo.setHostName("h1");
  hostInfo.setInterfaces("fip_4");
  hostInfo.setArchitecture("os_arch");
  hostInfo.setOS("os_type");
  hostInfo.setMemoryTotal(10);
  hostInfo.setMemorySize(100);
  hostInfo.setProcessorCount(10);
  List<DiskInfo> mounts=new ArrayList<DiskInfo>();
  mounts.add(new DiskInfo("/dev/sda","/mnt/disk1","5000000","4000000","10%","size","fstype"));
  hostInfo.setMounts(mounts);
  AgentVersion agentVersion=new AgentVersion("0.0.x");
  long currentTime=1001;
  clusters.getHost("h1").handleEvent(new HostRegistrationRequestEvent("h1",agentVersion,currentTime,hostInfo));
  Assert.assertEquals(HostState.WAITING_FOR_HOST_STATUS_UPDATES,clusters.getHost("h1").getState());
  clusters.getHost("h1").setState(HostState.HEARTBEAT_LOST);
  try {
    clusters.getHost("h1").handleEvent(new HostHealthyHeartbeatEvent("h1",currentTime));
    fail("Exception should be thrown on invalid event");
  }
 catch (  InvalidStateTransitionException e) {
  }
}
