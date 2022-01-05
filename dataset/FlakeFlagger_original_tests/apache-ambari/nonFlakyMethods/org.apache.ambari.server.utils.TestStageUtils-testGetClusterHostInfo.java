@Test public void testGetClusterHostInfo() throws AmbariException {
  Clusters fsm=injector.getInstance(Clusters.class);
  fsm.addCluster("c1");
  fsm.addHost("h1");
  fsm.addHost("h2");
  fsm.addHost("h3");
  fsm.getCluster("c1").setDesiredStackVersion(new StackId("HDP-0.1"));
  fsm.getHost("h1").setOsType("centos5");
  fsm.getHost("h2").setOsType("centos5");
  fsm.getHost("h3").setOsType("centos5");
  fsm.getHost("h1").persist();
  fsm.getHost("h2").persist();
  fsm.getHost("h3").persist();
  fsm.mapHostToCluster("h1","c1");
  fsm.mapHostToCluster("h2","c1");
  fsm.mapHostToCluster("h3","c1");
  String[] hostList={"h1","h2","h3"};
  addHdfsService(fsm.getCluster("c1"),hostList,injector);
  addHbaseService(fsm.getCluster("c1"),hostList,injector);
  Map<String,List<String>> info=StageUtils.getClusterHostInfo(fsm.getCluster("c1"));
  assertEquals(2,info.get("slave_hosts").size());
  assertEquals(1,info.get("hbase_master_host").size());
  assertEquals("h1",info.get("hbase_master_host").get(0));
}
