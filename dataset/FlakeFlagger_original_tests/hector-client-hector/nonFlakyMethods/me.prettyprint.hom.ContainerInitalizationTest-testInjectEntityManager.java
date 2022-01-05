@Test public void testInjectEntityManager(){
  EntityManager entityManager=entityManagerFactory.createEntityManager();
  assertTrue(entityManagerFactory.isOpen());
}
