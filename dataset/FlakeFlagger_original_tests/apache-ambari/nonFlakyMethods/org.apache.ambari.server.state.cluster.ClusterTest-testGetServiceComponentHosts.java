@Test public void testGetServiceComponentHosts() throws AmbariException {
  Service s=serviceFactory.createNew(c1,"HDFS");
  c1.addService(s);
  s.persist();
  ServiceComponent sc=serviceComponentFactory.createNew(s,"NAMENODE");
  s.addServiceComponent(sc);
  sc.persist();
  ServiceComponentHost sch=serviceComponentHostFactory.createNew(sc,"h1",false);
  sc.addServiceComponentHost(sch);
  sch.persist();
  List<ServiceComponentHost> scHosts=c1.getServiceComponentHosts("h1");
  Assert.assertEquals(1,scHosts.size());
}
