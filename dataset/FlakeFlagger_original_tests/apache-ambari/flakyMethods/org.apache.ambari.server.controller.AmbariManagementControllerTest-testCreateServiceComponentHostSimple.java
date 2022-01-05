@Test public void testCreateServiceComponentHostSimple() throws AmbariException {
  String clusterName="foo1";
  createCluster(clusterName);
  clusters.getCluster(clusterName).setDesiredStackVersion(new StackId("HDP-0.1"));
  String serviceName="HDFS";
  createService(clusterName,serviceName,null);
  String componentName1="NAMENODE";
  String componentName2="DATANODE";
  String componentName3="HDFS_CLIENT";
  createServiceComponent(clusterName,serviceName,componentName1,State.INIT);
  createServiceComponent(clusterName,serviceName,componentName2,State.INIT);
  createServiceComponent(clusterName,serviceName,componentName3,State.INIT);
  String host1="h1";
  clusters.addHost(host1);
  clusters.getHost("h1").setOsType("centos5");
  clusters.getHost("h1").persist();
  String host2="h2";
  clusters.addHost(host2);
  clusters.getHost("h2").setOsType("centos5");
  clusters.getHost("h2").persist();
  clusters.getHost("h1").setOsType("centos5");
  clusters.getHost("h2").setOsType("centos6");
  try {
    createServiceComponentHost(clusterName,serviceName,componentName1,host1,State.INIT);
    fail("ServiceComponentHost creation should fail for invalid host" + " as host not mapped to cluster");
  }
 catch (  Exception e) {
  }
  clusters.mapHostToCluster(host1,clusterName);
  clusters.mapHostToCluster(host2,clusterName);
  try {
    createServiceComponentHost(clusterName,serviceName,componentName1,host1,State.INSTALLING);
    fail("ServiceComponentHost creation should fail for invalid state");
  }
 catch (  Exception e) {
    e.printStackTrace();
  }
  try {
    clusters.getCluster(clusterName).getService(serviceName).getServiceComponent(componentName1).getServiceComponentHost(host1);
    fail("ServiceComponentHost creation should have failed earlier");
  }
 catch (  Exception e) {
  }
  createServiceComponentHost(clusterName,null,componentName1,host1,null);
  createServiceComponentHost(clusterName,serviceName,componentName2,host1,null);
  createServiceComponentHost(clusterName,serviceName,componentName2,host2,null);
  createServiceComponentHost(clusterName,serviceName,componentName3,host1,null);
  createServiceComponentHost(clusterName,serviceName,componentName3,host2,null);
  try {
    createServiceComponentHost(clusterName,serviceName,componentName1,host1,null);
    fail("ServiceComponentHost creation should fail as duplicate");
  }
 catch (  Exception e) {
  }
  Assert.assertNotNull(clusters.getCluster(clusterName).getService(serviceName).getServiceComponent(componentName1).getServiceComponentHost(host1));
  Assert.assertNotNull(clusters.getCluster(clusterName).getService(serviceName).getServiceComponent(componentName2).getServiceComponentHost(host1));
  Assert.assertNotNull(clusters.getCluster(clusterName).getService(serviceName).getServiceComponent(componentName2).getServiceComponentHost(host2));
  Assert.assertNotNull(clusters.getCluster(clusterName).getService(serviceName).getServiceComponent(componentName3).getServiceComponentHost(host1));
  Assert.assertNotNull(clusters.getCluster(clusterName).getService(serviceName).getServiceComponent(componentName3).getServiceComponentHost(host2));
  ServiceComponentHostRequest r=new ServiceComponentHostRequest(clusterName,serviceName,componentName2,null,null,null);
  Set<ServiceComponentHostResponse> response=controller.getHostComponents(Collections.singleton(r));
  Assert.assertEquals(2,response.size());
}
