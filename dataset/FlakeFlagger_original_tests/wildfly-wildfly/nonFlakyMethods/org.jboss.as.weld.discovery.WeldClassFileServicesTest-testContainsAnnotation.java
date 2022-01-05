@Test public void testContainsAnnotation(){
  Assert.assertTrue(alpha.containsAnnotation(Vetoed.class));
  Assert.assertTrue(innerInterface.containsAnnotation(Named.class));
  Assert.assertFalse(bravo.containsAnnotation(Vetoed.class));
  Assert.assertFalse(bravo.containsAnnotation(Named.class));
  Assert.assertTrue(bravo.containsAnnotation(Inject.class));
}
