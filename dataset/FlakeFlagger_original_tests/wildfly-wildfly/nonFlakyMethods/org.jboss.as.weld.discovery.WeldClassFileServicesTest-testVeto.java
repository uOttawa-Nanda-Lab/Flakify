@Test public void testVeto() throws IOException {
  Assert.assertTrue(alpha.isVetoed());
  Assert.assertFalse(abstractAlpha.isVetoed());
  Assert.assertFalse(alphaImpl.isVetoed());
  Assert.assertTrue(bravo.isVetoed());
}
