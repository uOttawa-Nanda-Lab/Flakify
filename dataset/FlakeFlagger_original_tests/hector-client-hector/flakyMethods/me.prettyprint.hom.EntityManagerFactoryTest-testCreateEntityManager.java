@Test public void testCreateEntityManager(){
  EntityManager entityManager=entityManagerFactory.createEntityManager();
  assertNotNull(entityManager);
  assertTrue(entityManagerFactory.isOpen());
}
