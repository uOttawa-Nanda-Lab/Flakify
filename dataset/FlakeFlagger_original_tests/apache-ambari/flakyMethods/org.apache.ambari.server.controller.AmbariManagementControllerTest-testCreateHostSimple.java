@Test public void testCreateHostSimple() throws AmbariException {
  List<String> clusterNames=null;
  Map<String,String> hostAttributes=null;
  HostRequest r1=new HostRequest("h1",clusterNames,hostAttributes);
  r1.toString();
  Set<HostRequest> requests=new HashSet<HostRequest>();
  requests.add(r1);
  try {
    controller.createHosts(requests);
    fail("Create host should fail for non-bootstrapped host");
  }
 catch (  Exception e) {
  }
  clusters.addHost("h1");
  clusters.addHost("h2");
  clusters.getHost("h1").setOsType("centos5");
  clusters.getHost("h2").setOsType("centos5");
  clusters.getHost("h1").persist();
  clusters.getHost("h2").persist();
  clusterNames=new ArrayList<String>();
  clusterNames.add("foo1");
  clusterNames.add("foo2");
  hostAttributes=new HashMap<String,String>();
  HostRequest r2=new HostRequest("h2",clusterNames,hostAttributes);
  requests.add(r2);
  try {
    controller.createHosts(requests);
    fail("Create host should fail for invalid clusters");
  }
 catch (  Exception e) {
  }
  clusters.addCluster("foo1");
  clusters.addCluster("foo2");
  clusters.getCluster("foo1").setDesiredStackVersion(new StackId("HDP-0.1"));
  clusters.getCluster("foo2").setDesiredStackVersion(new StackId("HDP-0.1"));
  controller.createHosts(requests);
  Assert.assertNotNull(clusters.getHost("h1"));
  Assert.assertNotNull(clusters.getHost("h2"));
  Assert.assertEquals(0,clusters.getClustersForHost("h1").size());
  Assert.assertEquals(2,clusters.getClustersForHost("h2").size());
}
