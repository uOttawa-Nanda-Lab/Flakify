@Test public void testDebugDump() throws AmbariException {
  String c1="c1";
  String c2="c2";
  String h1="h1";
  String h2="h2";
  String h3="h3";
  clusters.addCluster(c1);
  clusters.addCluster(c2);
  clusters.getCluster(c1).setDesiredStackVersion(new StackId("HDP-0.1"));
  clusters.getCluster(c2).setDesiredStackVersion(new StackId("HDP-0.1"));
  clusters.addHost(h1);
  clusters.addHost(h2);
  clusters.addHost(h3);
  clusters.getHost(h1).setOsType("redhat6");
  clusters.getHost(h2).setOsType("centos5");
  clusters.getHost(h3).setOsType("centos6");
  clusters.getHost(h1).persist();
  clusters.getHost(h2).persist();
  clusters.getHost(h3).persist();
  clusters.mapHostToCluster(h1,c1);
  clusters.mapHostToCluster(h2,c1);
  StringBuilder sb=new StringBuilder();
  clusters.debugDump(sb);
}
