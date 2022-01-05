@Test public void testIsAssignableFrom(){
  Assert.assertTrue(alpha.isAssignableFrom(AlphaImpl.class));
  Assert.assertTrue(abstractAlpha.isAssignableFrom(AlphaImpl.class));
  Assert.assertFalse(abstractAlpha.isAssignableFrom(Alpha.class));
  Assert.assertTrue(innerInterface.isAssignableFrom(Bravo.class));
  Assert.assertTrue(alphaImpl.isAssignableFrom(Bravo.class));
}
