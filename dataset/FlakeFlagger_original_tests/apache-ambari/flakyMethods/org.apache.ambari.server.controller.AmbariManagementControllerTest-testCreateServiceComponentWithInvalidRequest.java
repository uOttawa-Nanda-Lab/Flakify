@Test public void testCreateServiceComponentWithInvalidRequest() throws AmbariException {
  Set<ServiceComponentRequest> set1=new HashSet<ServiceComponentRequest>();
  try {
    set1.clear();
    ServiceComponentRequest rInvalid=new ServiceComponentRequest(null,null,null,null,null);
    set1.add(rInvalid);
    controller.createComponents(set1);
    fail("Expected failure for invalid requests");
  }
 catch (  Exception e) {
  }
  try {
    set1.clear();
    ServiceComponentRequest rInvalid=new ServiceComponentRequest("c1",null,null,null,null);
    set1.add(rInvalid);
    controller.createComponents(set1);
    fail("Expected failure for invalid requests");
  }
 catch (  Exception e) {
  }
  try {
    set1.clear();
    ServiceComponentRequest rInvalid=new ServiceComponentRequest("c1","s1",null,null,null);
    set1.add(rInvalid);
    controller.createComponents(set1);
    fail("Expected failure for invalid requests");
  }
 catch (  Exception e) {
  }
  try {
    set1.clear();
    ServiceComponentRequest rInvalid=new ServiceComponentRequest("c1","s1","sc1",null,null);
    set1.add(rInvalid);
    controller.createComponents(set1);
    fail("Expected failure for invalid cluster");
  }
 catch (  ClusterNotFoundException e) {
  }
  clusters.addCluster("c1");
  clusters.addCluster("c2");
  try {
    set1.clear();
    ServiceComponentRequest rInvalid=new ServiceComponentRequest("c1","HDFS","NAMENODE",null,null);
    set1.add(rInvalid);
    controller.createComponents(set1);
    fail("Expected failure for invalid service");
  }
 catch (  ServiceNotFoundException e) {
  }
  Cluster c1=clusters.getCluster("c1");
  c1.setDesiredStackVersion(new StackId("HDP-0.1"));
  Service s1=serviceFactory.createNew(c1,"HDFS");
  Service s2=serviceFactory.createNew(c1,"MAPREDUCE");
  c1.addService(s1);
  c1.addService(s2);
  s1.persist();
  s2.persist();
  set1.clear();
  ServiceComponentRequest valid1=new ServiceComponentRequest("c1","HDFS","NAMENODE",null,null);
  ServiceComponentRequest valid2=new ServiceComponentRequest("c1","MAPREDUCE","JOBTRACKER",null,null);
  ServiceComponentRequest valid3=new ServiceComponentRequest("c1","MAPREDUCE","TASKTRACKER",null,null);
  set1.add(valid1);
  set1.add(valid2);
  set1.add(valid3);
  controller.createComponents(set1);
  try {
    set1.clear();
    ServiceComponentRequest rInvalid1=new ServiceComponentRequest("c1","HDFS","HDFS_CLIENT",null,null);
    ServiceComponentRequest rInvalid2=new ServiceComponentRequest("c1","HDFS","HDFS_CLIENT",null,null);
    set1.add(rInvalid1);
    set1.add(rInvalid2);
    controller.createComponents(set1);
    fail("Expected failure for dups in requests");
  }
 catch (  Exception e) {
  }
  try {
    set1.clear();
    ServiceComponentRequest rInvalid1=new ServiceComponentRequest("c1","HDFS","HDFS_CLIENT",null,null);
    ServiceComponentRequest rInvalid2=new ServiceComponentRequest("c2","HDFS","HDFS_CLIENT",null,null);
    set1.add(rInvalid1);
    set1.add(rInvalid2);
    controller.createComponents(set1);
    fail("Expected failure for multiple clusters");
  }
 catch (  Exception e) {
  }
  try {
    set1.clear();
    ServiceComponentRequest rInvalid=new ServiceComponentRequest("c1","HDFS","NAMENODE",null,null);
    set1.add(rInvalid);
    controller.createComponents(set1);
    fail("Expected failure for already existing component");
  }
 catch (  Exception e) {
  }
  Assert.assertEquals(1,s1.getServiceComponents().size());
  Assert.assertNotNull(s1.getServiceComponent("NAMENODE"));
  Assert.assertEquals(2,s2.getServiceComponents().size());
  Assert.assertNotNull(s2.getServiceComponent("JOBTRACKER"));
  Assert.assertNotNull(s2.getServiceComponent("TASKTRACKER"));
}
