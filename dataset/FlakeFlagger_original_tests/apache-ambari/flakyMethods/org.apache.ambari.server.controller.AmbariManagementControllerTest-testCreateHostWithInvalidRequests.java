@Test public void testCreateHostWithInvalidRequests() throws AmbariException {
  Set<HostRequest> set1=new HashSet<HostRequest>();
  try {
    set1.clear();
    HostRequest rInvalid=new HostRequest("h1",null,null);
    set1.add(rInvalid);
    controller.createHosts(set1);
    fail("Expected failure for invalid host");
  }
 catch (  Exception e) {
  }
  clusters.addHost("h1");
  List<String> clusterNames=new ArrayList<String>();
  clusterNames.add("c1");
  try {
    set1.clear();
    HostRequest rInvalid=new HostRequest("h1",clusterNames,null);
    set1.add(rInvalid);
    controller.createHosts(set1);
    fail("Expected failure for invalid cluster");
  }
 catch (  Exception e) {
  }
  clusters.addCluster("c1");
  try {
    set1.clear();
    HostRequest rInvalid1=new HostRequest("h1",clusterNames,null);
    HostRequest rInvalid2=new HostRequest("h1",clusterNames,null);
    set1.add(rInvalid1);
    set1.add(rInvalid2);
    controller.createHosts(set1);
    fail("Expected failure for dup requests");
  }
 catch (  Exception e) {
  }
}
