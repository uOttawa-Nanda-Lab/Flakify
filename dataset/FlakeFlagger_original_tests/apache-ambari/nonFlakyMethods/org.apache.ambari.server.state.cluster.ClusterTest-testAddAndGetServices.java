@Test public void testAddAndGetServices() throws AmbariException {
  Service s1=serviceFactory.createNew(c1,"HDFS");
  Service s2=serviceFactory.createNew(c1,"MAPREDUCE");
  c1.addService(s1);
  c1.addService(s2);
  s1.persist();
  s2.persist();
  Service s3=serviceFactory.createNew(c1,"MAPREDUCE");
  try {
    c1.addService(s3);
    fail("Expected error on adding dup service");
  }
 catch (  Exception e) {
  }
  Service s=c1.getService("HDFS");
  Assert.assertNotNull(s);
  Assert.assertEquals("HDFS",s.getName());
  Assert.assertEquals(c1.getClusterId(),s.getClusterId());
  try {
    c1.getService("HBASE");
    fail("Expected error for unknown service");
  }
 catch (  Exception e) {
  }
  Map<String,Service> services=c1.getServices();
  Assert.assertEquals(2,services.size());
  Assert.assertTrue(services.containsKey("HDFS"));
  Assert.assertTrue(services.containsKey("MAPREDUCE"));
}
