@Test public void testStateCommandsGeneration() throws AmbariException, InterruptedException, InvalidStateTransitionException {
  Clusters clusters=injector.getInstance(Clusters.class);
  clusters.addHost(hostname1);
  clusters.getHost(hostname1).setOsType("centos6");
  clusters.getHost(hostname1).persist();
  clusters.addHost(hostname2);
  clusters.getHost(hostname2).setOsType("centos6");
  clusters.getHost(hostname2).persist();
  clusters.addCluster(clusterName);
  Cluster cluster=clusters.getCluster(clusterName);
  cluster.setDesiredStackVersion(new StackId("HDP-0.1"));
  Set<String> hostNames=new HashSet<String>(){
{
      add(hostname1);
      add(hostname2);
    }
  }
;
  clusters.mapHostsToCluster(hostNames,clusterName);
  Service hdfs=cluster.addService(serviceName);
  hdfs.persist();
  hdfs.addServiceComponent(Role.DATANODE.name()).persist();
  hdfs.getServiceComponent(Role.DATANODE.name()).addServiceComponentHost(hostname1).persist();
  hdfs.addServiceComponent(Role.NAMENODE.name()).persist();
  hdfs.getServiceComponent(Role.NAMENODE.name()).addServiceComponentHost(hostname1).persist();
  hdfs.addServiceComponent(Role.SECONDARY_NAMENODE.name()).persist();
  hdfs.getServiceComponent(Role.SECONDARY_NAMENODE.name()).addServiceComponentHost(hostname1).persist();
  ActionQueue aq=new ActionQueue();
  ActionManager am=mock(ActionManager.class);
  HeartbeatMonitor hm=new HeartbeatMonitor(clusters,aq,am,heartbeatMonitorWakeupIntervalMS);
  HeartBeatHandler handler=new HeartBeatHandler(clusters,aq,am,injector);
  Register reg=new Register();
  reg.setHostname(hostname1);
  reg.setResponseId(12);
  reg.setTimestamp(System.currentTimeMillis() - 300);
  HostInfo hi=new HostInfo();
  hi.setOS("Centos5");
  reg.setHardwareProfile(hi);
  handler.handleRegistration(reg);
  HeartBeat hb=new HeartBeat();
  hb.setHostname(hostname1);
  hb.setNodeStatus(new HostStatus(HostStatus.Status.HEALTHY,"cool"));
  hb.setTimestamp(System.currentTimeMillis());
  hb.setResponseId(12);
  handler.handleHeartBeat(hb);
  List<StatusCommand> cmds=hm.generateStatusCommands(hostname1);
  assertTrue("HeartbeatMonitor should generate StatusCommands for host1",cmds.size() == 3);
  assertEquals("HDFS",cmds.get(0).getServiceName());
  boolean containsDATANODEStatus=false;
  boolean containsNAMENODEStatus=false;
  boolean containsSECONDARY_NAMENODEStatus=false;
  for (  StatusCommand cmd : cmds) {
    containsDATANODEStatus|=cmd.getComponentName().equals("DATANODE");
    containsNAMENODEStatus|=cmd.getComponentName().equals("NAMENODE");
    containsSECONDARY_NAMENODEStatus|=cmd.getComponentName().equals("SECONDARY_NAMENODE");
  }
  assertEquals(true,containsDATANODEStatus);
  assertEquals(true,containsNAMENODEStatus);
  assertEquals(true,containsSECONDARY_NAMENODEStatus);
  cmds=hm.generateStatusCommands(hostname2);
  assertTrue("HeartbeatMonitor should not generate StatusCommands for host2 because it has no services",cmds.isEmpty());
}
