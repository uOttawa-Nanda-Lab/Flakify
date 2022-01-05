@Test public void testTaskInProgressHandling() throws AmbariException, InvalidStateTransitionException {
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
  serviceComponentHost1.setState(State.INSTALLING);
  HeartBeat hb=new HeartBeat();
  hb.setTimestamp(System.currentTimeMillis());
  hb.setResponseId(0);
  hb.setHostname(hostname);
  hb.setNodeStatus(new HostStatus(Status.HEALTHY,"I am ok"));
  List<CommandReport> reports=new ArrayList<CommandReport>();
  CommandReport cr=new CommandReport();
  cr.setActionId(StageUtils.getActionId(requestId,stageId));
  cr.setTaskId(1);
  cr.setClusterName(clusterName);
  cr.setServiceName(serviceName);
  cr.setRole(componentName1);
  cr.setStatus("IN_PROGRESS");
  cr.setStdErr("none");
  cr.setStdOut("dummy output");
  cr.setExitCode(777);
  reports.add(cr);
  hb.setReports(reports);
  hb.setComponentStatus(new ArrayList<ComponentStatus>());
  handler.handleHeartBeat(hb);
  State componentState1=serviceComponentHost1.getState();
  assertEquals("Host state should still be installing",State.INSTALLING,componentState1);
}
