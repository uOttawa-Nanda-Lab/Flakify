@Test public void testAddingNewFile(){
  Assert.assertNotNull(getClass().getClassLoader().getResource("wildcard-new-file"));
}
