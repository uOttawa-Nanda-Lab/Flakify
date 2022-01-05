@Test public void testIsAssignableFromObject(){
  Assert.assertFalse(alpha.isAssignableFrom(Object.class));
  Assert.assertFalse(abstractAlpha.isAssignableFrom(Object.class));
  Assert.assertFalse(alphaImpl.isAssignableFrom(Object.class));
  Assert.assertFalse(bravo.isAssignableFrom(Object.class));
}
