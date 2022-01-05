@Test public void testCreateServicesWithInvalidRequest() throws AmbariException {
  Set<ServiceRequest> set1=new HashSet<ServiceRequest>();
  try {
    set1.clear();
    ServiceRequest rInvalid=new ServiceRequest(null,null,null,null);
    set1.add(rInvalid);
    controller.createServices(set1);
    fail("Expected failure for invalid requests");
  }
 catch (  Exception e) {
  }
  try {
    set1.clear();
    ServiceRequest rInvalid=new ServiceRequest("foo",null,null,null);
    set1.add(rInvalid);
    controller.createServices(set1);
    fail("Expected failure for invalid requests");
  }
 catch (  Exception e) {
  }
  try {
    set1.clear();
    ServiceRequest rInvalid=new ServiceRequest("foo","bar",null,null);
    set1.add(rInvalid);
    controller.createServices(set1);
    fail("Expected failure for invalid cluster");
  }
 catch (  ClusterNotFoundException e) {
  }
  clusters.addCluster("foo");
  clusters.addCluster("bar");
  clusters.getCluster("foo").setDesiredStackVersion(new StackId("HDP-0.1"));
  clusters.getCluster("bar").setDesiredStackVersion(new StackId("HDP-0.1"));
  try {
    set1.clear();
    ServiceRequest valid1=new ServiceRequest("foo","HDFS",null,null);
    ServiceRequest valid2=new ServiceRequest("foo","HDFS",null,null);
    set1.add(valid1);
    set1.add(valid2);
    controller.createServices(set1);
    fail("Expected failure for invalid requests");
  }
 catch (  Exception e) {
  }
  try {
    set1.clear();
    ServiceRequest valid1=new ServiceRequest("foo","bar",null,null);
    set1.add(valid1);
    controller.createServices(set1);
    fail("Expected failure for invalid service");
  }
 catch (  Exception e) {
  }
  try {
    set1.clear();
    ServiceRequest valid1=new ServiceRequest("foo","HDFS",null,null);
    ServiceRequest valid2=new ServiceRequest("bar","HDFS",null,null);
    set1.add(valid1);
    set1.add(valid2);
    controller.createServices(set1);
    fail("Expected failure for multiple clusters");
  }
 catch (  Exception e) {
  }
  Assert.assertNotNull(clusters.getCluster("foo"));
  Assert.assertEquals(0,clusters.getCluster("foo").getServices().size());
  set1.clear();
  ServiceRequest valid=new ServiceRequest("foo","HDFS",null,null);
  set1.add(valid);
  controller.createServices(set1);
  try {
    set1.clear();
    ServiceRequest valid1=new ServiceRequest("foo","HDFS",null,null);
    ServiceRequest valid2=new ServiceRequest("foo","HDFS",null,null);
    set1.add(valid1);
    set1.add(valid2);
    controller.createServices(set1);
    fail("Expected failure for existing service");
  }
 catch (  Exception e) {
  }
  Assert.assertEquals(1,clusters.getCluster("foo").getServices().size());
}
