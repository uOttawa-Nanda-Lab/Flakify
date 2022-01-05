/** 
 * persistence provider is responsible for returning empty collection if relation doesn't exists
 */
@Test public void testEmptyPersistentCollection(){
  String testClusterName="test_cluster2";
  ClusterEntity clusterEntity=new ClusterEntity();
  clusterEntity.setClusterName(testClusterName);
  ClusterDAO clusterDAO=injector.getInstance(ClusterDAO.class);
  clusterDAO.create(clusterEntity);
  clusterEntity=clusterDAO.findByName(clusterEntity.getClusterName());
  assertTrue("empty relation wasn't instantiated",clusterEntity.getHostEntities() != null);
}
