@Test public void testIsAssignableToObject(){
  Assert.assertTrue(alpha.isAssignableTo(Object.class));
  Assert.assertTrue(abstractAlpha.isAssignableTo(Object.class));
  Assert.assertTrue(alphaImpl.isAssignableTo(Object.class));
  Assert.assertTrue(bravo.isAssignableTo(Object.class));
}
