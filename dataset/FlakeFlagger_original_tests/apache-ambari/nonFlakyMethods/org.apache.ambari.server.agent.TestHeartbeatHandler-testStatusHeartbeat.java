@Test public void testStatusHeartbeat() throws Exception {
  ActionManager am=new ActionManager(0,0,null,null,new ActionDBInMemoryImpl());
  final String hostname="host1";
  String clusterName="cluster1";
  String serviceName="HDFS";
  String componentName1="DATANODE";
  String componentName2="NAMENODE";
  clusters.addHost(hostname);
  clusters.getHost(hostname).setOsType("centos5");
  clusters.getHost(hostname).persist();
  clusters.addCluster(clusterName);
  Cluster cluster=clusters.getCluster(clusterName);
  cluster.setDesiredStackVersion(new StackId("HDP-0.1"));
  @SuppressWarnings("serial") Set<String> hostNames=new HashSet<String>(){
{
      add(hostname);
    }
  }
;
  clusters.mapHostsToCluster(hostNames,clusterName);
  Service hdfs=cluster.addService(serviceName);
  hdfs.persist();
  hdfs.addServiceComponent(Role.DATANODE.name()).persist();
  hdfs.getServiceComponent(Role.DATANODE.name()).addServiceComponentHost(hostname).persist();
  hdfs.addServiceComponent(Role.NAMENODE.name()).persist();
  hdfs.getServiceComponent(Role.NAMENODE.name()).addServiceComponentHost(hostname).persist();
  hdfs.addServiceComponent(Role.SECONDARY_NAMENODE.name()).persist();
  hdfs.getServiceComponent(Role.SECONDARY_NAMENODE.name()).addServiceComponentHost(hostname).persist();
  ActionQueue aq=new ActionQueue();
  HeartBeatHandler handler=new HeartBeatHandler(clusters,aq,am,injector);
  Register reg=new Register();
  HostInfo hi=new HostInfo();
  hi.setHostName("host1");
  hi.setOS("CentOS");
  hi.setOSRelease("5.8");
  reg.setHostname(hostname);
  reg.setResponseId(0);
  reg.setHardwareProfile(hi);
  handler.handleRegistration(reg);
  ServiceComponentHost serviceComponentHost1=clusters.getCluster(clusterName).getService(serviceName).getServiceComponent(componentName1).getServiceComponentHost(hostname);
  ServiceComponentHost serviceComponentHost2=clusters.getCluster(clusterName).getService(serviceName).getServiceComponent(componentName2).getServiceComponentHost(hostname);
  serviceComponentHost1.setState(State.INSTALLED);
  serviceComponentHost2.setState(State.INSTALLED);
  HeartBeat hb=new HeartBeat();
  hb.setTimestamp(System.currentTimeMillis());
  hb.setResponseId(0);
  hb.setHostname(hostname);
  hb.setNodeStatus(new HostStatus(Status.HEALTHY,"I am ok"));
  hb.setReports(new ArrayList<CommandReport>());
  ArrayList<ComponentStatus> componentStatuses=new ArrayList<ComponentStatus>();
  ComponentStatus componentStatus1=new ComponentStatus();
  componentStatus1.setClusterName(clusterName);
  componentStatus1.setServiceName(serviceName);
  componentStatus1.setMessage("I am ok");
  componentStatus1.setStatus(State.STARTED.name());
  componentStatus1.setComponentName(componentName1);
  componentStatuses.add(componentStatus1);
  hb.setComponentStatus(componentStatuses);
  handler.handleHeartBeat(hb);
  State componentState1=serviceComponentHost1.getState();
  State componentState2=serviceComponentHost2.getState();
  assertEquals(State.STARTED,componentState1);
  assertEquals(State.INSTALLED,componentState2);
}
