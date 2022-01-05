@Test public void testCreateServiceComponentMultiple() throws AmbariException {
  clusters.addCluster("c1");
  clusters.addCluster("c2");
  Cluster c1=clusters.getCluster("c1");
  c1.setDesiredStackVersion(new StackId("HDP-0.2"));
  Service s1=serviceFactory.createNew(c1,"HDFS");
  Service s2=serviceFactory.createNew(c1,"MAPREDUCE");
  c1.addService(s1);
  c1.addService(s2);
  s1.persist();
  s2.persist();
  Set<ServiceComponentRequest> set1=new HashSet<ServiceComponentRequest>();
  ServiceComponentRequest valid1=new ServiceComponentRequest("c1","HDFS","NAMENODE",null,null);
  ServiceComponentRequest valid2=new ServiceComponentRequest("c1","MAPREDUCE","JOBTRACKER",null,null);
  ServiceComponentRequest valid3=new ServiceComponentRequest("c1","MAPREDUCE","TASKTRACKER",null,null);
  set1.add(valid1);
  set1.add(valid2);
  set1.add(valid3);
  controller.createComponents(set1);
  Assert.assertEquals(1,c1.getService("HDFS").getServiceComponents().size());
  Assert.assertEquals(2,c1.getService("MAPREDUCE").getServiceComponents().size());
  Assert.assertNotNull(c1.getService("HDFS").getServiceComponent("NAMENODE"));
  Assert.assertNotNull(c1.getService("MAPREDUCE").getServiceComponent("JOBTRACKER"));
  Assert.assertNotNull(c1.getService("MAPREDUCE").getServiceComponent("TASKTRACKER"));
}
