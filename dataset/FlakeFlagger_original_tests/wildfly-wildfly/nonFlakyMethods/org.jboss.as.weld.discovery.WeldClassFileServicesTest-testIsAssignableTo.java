@Test public void testIsAssignableTo(){
  Assert.assertTrue(alphaImpl.isAssignableTo(Alpha.class));
  Assert.assertTrue(abstractAlpha.isAssignableTo(Alpha.class));
  Assert.assertFalse(abstractAlpha.isAssignableTo(AlphaImpl.class));
  Assert.assertTrue(bravo.isAssignableTo(InnerInterface.class));
  Assert.assertTrue(bravo.isAssignableTo(AbstractAlpha.class));
  Assert.assertFalse(bravo.isAssignableTo(InnerClasses.class));
}
