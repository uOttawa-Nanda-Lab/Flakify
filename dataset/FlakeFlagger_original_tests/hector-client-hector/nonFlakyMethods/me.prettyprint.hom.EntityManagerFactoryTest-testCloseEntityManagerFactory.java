@Test public void testCloseEntityManagerFactory(){
  assertTrue(entityManagerFactory.isOpen());
  entityManagerFactory.close();
  assertFalse(entityManagerFactory.isOpen());
}
