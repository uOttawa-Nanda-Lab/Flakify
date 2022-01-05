@Test public void testCreateServicesMultiple() throws AmbariException {
  Set<ServiceRequest> set1=new HashSet<ServiceRequest>();
  clusters.addCluster("foo");
  clusters.getCluster("foo").setDesiredStackVersion(new StackId("HDP-0.1"));
  ServiceRequest valid1=new ServiceRequest("foo","HDFS",null,null);
  ServiceRequest valid2=new ServiceRequest("foo","MAPREDUCE",null,null);
  set1.add(valid1);
  set1.add(valid2);
  controller.createServices(set1);
  try {
    valid1=new ServiceRequest("foo","bar",null,null);
    valid2=new ServiceRequest("foo","MAPREDUCE",null,null);
    set1.add(valid1);
    set1.add(valid2);
    controller.createServices(set1);
    fail("Expected failure for invalid services");
  }
 catch (  IllegalArgumentException e) {
  }
  Assert.assertNotNull(clusters.getCluster("foo"));
  Assert.assertEquals(2,clusters.getCluster("foo").getServices().size());
  Assert.assertNotNull(clusters.getCluster("foo").getService("HDFS"));
  Assert.assertNotNull(clusters.getCluster("foo").getService("MAPREDUCE"));
}
