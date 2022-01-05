@Test public void testCreateServiceComponentHostWithInvalidRequest() throws AmbariException {
  Set<ServiceComponentHostRequest> set1=new HashSet<ServiceComponentHostRequest>();
  try {
    set1.clear();
    ServiceComponentHostRequest rInvalid=new ServiceComponentHostRequest(null,null,null,null,null,null);
    set1.add(rInvalid);
    controller.createHostComponents(set1);
    fail("Expected failure for invalid requests");
  }
 catch (  IllegalArgumentException e) {
  }
  try {
    set1.clear();
    ServiceComponentHostRequest rInvalid=new ServiceComponentHostRequest("foo",null,null,null,null,null);
    set1.add(rInvalid);
    controller.createHostComponents(set1);
    fail("Expected failure for invalid requests");
  }
 catch (  IllegalArgumentException e) {
  }
  try {
    set1.clear();
    ServiceComponentHostRequest rInvalid=new ServiceComponentHostRequest("foo","HDFS",null,null,null,null);
    set1.add(rInvalid);
    controller.createHostComponents(set1);
    fail("Expected failure for invalid requests");
  }
 catch (  IllegalArgumentException e) {
  }
  try {
    set1.clear();
    ServiceComponentHostRequest rInvalid=new ServiceComponentHostRequest("foo","HDFS","NAMENODE",null,null,null);
    set1.add(rInvalid);
    controller.createHostComponents(set1);
    fail("Expected failure for invalid requests");
  }
 catch (  IllegalArgumentException e) {
  }
  try {
    set1.clear();
    ServiceComponentHostRequest rInvalid=new ServiceComponentHostRequest("foo","HDFS","NAMENODE","h1",null,null);
    set1.add(rInvalid);
    controller.createHostComponents(set1);
    fail("Expected failure for invalid cluster");
  }
 catch (  ClusterNotFoundException e) {
  }
  clusters.addCluster("foo");
  clusters.addCluster("c1");
  clusters.addCluster("c2");
  Cluster foo=clusters.getCluster("foo");
  Cluster c1=clusters.getCluster("c1");
  Cluster c2=clusters.getCluster("c2");
  foo.setDesiredStackVersion(new StackId("HDP-0.2"));
  c1.setDesiredStackVersion(new StackId("HDP-0.2"));
  c2.setDesiredStackVersion(new StackId("HDP-0.2"));
  try {
    set1.clear();
    ServiceComponentHostRequest rInvalid=new ServiceComponentHostRequest("foo","HDFS","NAMENODE","h1",null,null);
    set1.add(rInvalid);
    controller.createHostComponents(set1);
    fail("Expected failure for invalid service");
  }
 catch (  AmbariException e) {
  }
  Service s1=serviceFactory.createNew(foo,"HDFS");
  foo.addService(s1);
  s1.persist();
  Service s2=serviceFactory.createNew(c1,"HDFS");
  c1.addService(s2);
  s2.persist();
  Service s3=serviceFactory.createNew(c2,"HDFS");
  c2.addService(s3);
  s3.persist();
  try {
    set1.clear();
    ServiceComponentHostRequest rInvalid=new ServiceComponentHostRequest("foo","HDFS","NAMENODE","h1",null,null);
    set1.add(rInvalid);
    controller.createHostComponents(set1);
    fail("Expected failure for invalid service");
  }
 catch (  AmbariException e) {
  }
  ServiceComponent sc1=serviceComponentFactory.createNew(s1,"NAMENODE");
  s1.addServiceComponent(sc1);
  sc1.persist();
  ServiceComponent sc2=serviceComponentFactory.createNew(s2,"NAMENODE");
  s2.addServiceComponent(sc2);
  sc2.persist();
  ServiceComponent sc3=serviceComponentFactory.createNew(s3,"NAMENODE");
  s3.addServiceComponent(sc3);
  sc3.persist();
  try {
    set1.clear();
    ServiceComponentHostRequest rInvalid=new ServiceComponentHostRequest("foo","HDFS","NAMENODE","h1",null,null);
    set1.add(rInvalid);
    controller.createHostComponents(set1);
    fail("Expected failure for invalid host");
  }
 catch (  AmbariException e) {
  }
  clusters.addHost("h1");
  Host h1=clusters.getHost("h1");
  h1.setIPv4("ipv41");
  h1.setIPv6("ipv61");
  h1.setOsType("centos6");
  h1.persist();
  clusters.addHost("h2");
  Host h2=clusters.getHost("h2");
  h2.setIPv4("ipv42");
  h2.setIPv6("ipv62");
  h2.setOsType("centos6");
  h2.persist();
  clusters.addHost("h3");
  Host h3=clusters.getHost("h3");
  h3.setIPv4("ipv43");
  h3.setIPv6("ipv63");
  h3.setOsType("centos6");
  h3.persist();
  try {
    set1.clear();
    ServiceComponentHostRequest rInvalid=new ServiceComponentHostRequest("foo","HDFS","NAMENODE","h1",null,null);
    set1.add(rInvalid);
    controller.createHostComponents(set1);
    fail("Expected failure for invalid host cluster mapping");
  }
 catch (  AmbariException e) {
  }
  Set<String> hostnames=new HashSet<String>();
  hostnames.add("h1");
  hostnames.add("h2");
  hostnames.add("h3");
  clusters.mapHostsToCluster(hostnames,"foo");
  clusters.mapHostsToCluster(hostnames,"c1");
  clusters.mapHostsToCluster(hostnames,"c2");
  set1.clear();
  ServiceComponentHostRequest valid=new ServiceComponentHostRequest("foo","HDFS","NAMENODE","h1",null,null);
  set1.add(valid);
  controller.createHostComponents(set1);
  try {
    set1.clear();
    ServiceComponentHostRequest rInvalid1=new ServiceComponentHostRequest("foo","HDFS","NAMENODE","h2",null,null);
    ServiceComponentHostRequest rInvalid2=new ServiceComponentHostRequest("foo","HDFS","NAMENODE","h2",null,null);
    set1.add(rInvalid1);
    set1.add(rInvalid2);
    controller.createHostComponents(set1);
    fail("Expected failure for dup requests");
  }
 catch (  IllegalArgumentException e) {
  }
  try {
    set1.clear();
    ServiceComponentHostRequest rInvalid1=new ServiceComponentHostRequest("c1","HDFS","NAMENODE","h2",null,null);
    ServiceComponentHostRequest rInvalid2=new ServiceComponentHostRequest("c2","HDFS","NAMENODE","h3",null,null);
    set1.add(rInvalid1);
    set1.add(rInvalid2);
    controller.createHostComponents(set1);
    fail("Expected failure for multiple clusters");
  }
 catch (  IllegalArgumentException e) {
  }
  try {
    set1.clear();
    ServiceComponentHostRequest rInvalid1=new ServiceComponentHostRequest("foo","HDFS","NAMENODE","h1",null,null);
    ServiceComponentHostRequest rInvalid2=new ServiceComponentHostRequest("foo","HDFS","NAMENODE","h2",null,null);
    set1.add(rInvalid1);
    set1.add(rInvalid2);
    controller.createHostComponents(set1);
    fail("Expected failure for already existing");
  }
 catch (  IllegalArgumentException e) {
  }
  Assert.assertEquals(1,foo.getServiceComponentHosts("h1").size());
  Assert.assertEquals(0,foo.getServiceComponentHosts("h2").size());
  Assert.assertEquals(0,foo.getServiceComponentHosts("h3").size());
  set1.clear();
  ServiceComponentHostRequest valid1=new ServiceComponentHostRequest("c1","HDFS","NAMENODE","h1",null,null);
  set1.add(valid1);
  controller.createHostComponents(set1);
  set1.clear();
  ServiceComponentHostRequest valid2=new ServiceComponentHostRequest("c2","HDFS","NAMENODE","h1",null,null);
  set1.add(valid2);
  controller.createHostComponents(set1);
  Assert.assertEquals(1,foo.getServiceComponentHosts("h1").size());
  Assert.assertEquals(1,c1.getServiceComponentHosts("h1").size());
  Assert.assertEquals(1,c2.getServiceComponentHosts("h1").size());
}
