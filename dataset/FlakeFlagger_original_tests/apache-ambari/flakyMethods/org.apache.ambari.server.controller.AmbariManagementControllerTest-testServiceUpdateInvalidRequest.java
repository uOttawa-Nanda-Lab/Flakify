@Test public void testServiceUpdateInvalidRequest() throws AmbariException {
  String clusterName1="foo1";
  createCluster(clusterName1);
  String clusterName2="foo2";
  createCluster(clusterName2);
  String serviceName1="HDFS";
  createService(clusterName1,serviceName1,null);
  String serviceName2="HBASE";
  String serviceName3="HBASE";
  try {
    createService(clusterName2,serviceName3,null);
    fail("Expected fail for invalid service for stack 0.1");
  }
 catch (  Exception e) {
  }
  clusters.getCluster(clusterName1).setDesiredStackVersion(new StackId("HDP-0.2"));
  clusters.getCluster(clusterName2).setDesiredStackVersion(new StackId("HDP-0.2"));
  createService(clusterName1,serviceName2,null);
  createService(clusterName2,serviceName3,null);
  Set<ServiceRequest> reqs=new HashSet<ServiceRequest>();
  ServiceRequest req1, req2;
  try {
    reqs.clear();
    req1=new ServiceRequest(clusterName1,serviceName1,null,State.INSTALLED.toString());
    req2=new ServiceRequest(clusterName2,serviceName2,null,State.INSTALLED.toString());
    reqs.add(req1);
    reqs.add(req2);
    controller.updateServices(reqs);
    fail("Expected failure for multi cluster update");
  }
 catch (  Exception e) {
  }
  try {
    reqs.clear();
    req1=new ServiceRequest(clusterName1,serviceName1,null,State.INSTALLED.toString());
    req2=new ServiceRequest(clusterName1,serviceName1,null,State.INSTALLED.toString());
    reqs.add(req1);
    reqs.add(req2);
    controller.updateServices(reqs);
    fail("Expected failure for dups services");
  }
 catch (  Exception e) {
  }
  clusters.getCluster(clusterName1).getService(serviceName2).setDesiredState(State.INSTALLED);
  try {
    reqs.clear();
    req1=new ServiceRequest(clusterName1,serviceName1,null,State.INSTALLED.toString());
    req2=new ServiceRequest(clusterName1,serviceName2,null,State.STARTED.toString());
    reqs.add(req1);
    reqs.add(req2);
    controller.updateServices(reqs);
    fail("Expected failure for different states");
  }
 catch (  Exception e) {
  }
}
