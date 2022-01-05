/** 
 * Rollback test
 */
@Test public void testSafeRollback(){
  String testClusterName="don't save";
  EntityManager entityManager=injector.getInstance(OrmTestHelper.class).getEntityManager();
  entityManager.getTransaction().begin();
  ClusterEntity clusterEntity=new ClusterEntity();
  clusterEntity.setClusterName(testClusterName);
  entityManager.persist(clusterEntity);
  entityManager.getTransaction().rollback();
  assertNull("transaction was not rolled back",injector.getInstance(ClusterDAO.class).findByName(testClusterName));
}
