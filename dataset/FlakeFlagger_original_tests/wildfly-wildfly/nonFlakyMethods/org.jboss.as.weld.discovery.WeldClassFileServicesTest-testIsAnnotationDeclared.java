@Test public void testIsAnnotationDeclared(){
  Assert.assertTrue(alpha.isAnnotationDeclared(Vetoed.class));
  Assert.assertTrue(innerInterface.isAnnotationDeclared(Named.class));
  Assert.assertFalse(bravo.isAnnotationDeclared(Vetoed.class));
  Assert.assertFalse(bravo.isAnnotationDeclared(Named.class));
  Assert.assertFalse(bravo.isAnnotationDeclared(Inject.class));
}
