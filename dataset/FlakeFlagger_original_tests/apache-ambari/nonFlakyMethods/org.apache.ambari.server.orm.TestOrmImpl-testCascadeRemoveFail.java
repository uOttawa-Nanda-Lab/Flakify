/** 
 * to clarify: are cascade operations allowed?
 */
@Test public void testCascadeRemoveFail(){
  ClusterServiceDAO clusterServiceDAO=injector.getInstance(ClusterServiceDAO.class);
  Date currentTime=new Date();
  String serviceName="MapReduce2";
  String clusterName="test_cluster1";
  createService(currentTime,serviceName,clusterName);
  ClusterServiceEntity clusterServiceEntity=clusterServiceDAO.findByClusterAndServiceNames(clusterName,serviceName);
  clusterServiceDAO.remove(clusterServiceEntity);
  Assert.assertNull(clusterServiceDAO.findByClusterAndServiceNames(clusterName,serviceName));
}
