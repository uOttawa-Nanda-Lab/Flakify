/** 
 * Test auto incremented field and custom query example
 */
@Test public void testAutoIncrementedField(){
  ClusterServiceDAO clusterServiceDAO=injector.getInstance(ClusterServiceDAO.class);
  Date currentTime=new Date();
  String serviceName="MapReduce1";
  String clusterName="test_cluster1";
  createService(currentTime,serviceName,clusterName);
  ClusterServiceEntity clusterServiceEntity=clusterServiceDAO.findByClusterAndServiceNames(clusterName,serviceName);
  clusterServiceDAO.remove(clusterServiceEntity);
  assertNull(clusterServiceDAO.findByClusterAndServiceNames(clusterName,serviceName));
}
