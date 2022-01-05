@Test public void testModifiers() throws IOException {
  Assert.assertTrue(Modifier.isAbstract(alpha.getModifiers()));
  Assert.assertTrue(Modifier.isAbstract(abstractAlpha.getModifiers()));
  Assert.assertFalse(Modifier.isAbstract(alphaImpl.getModifiers()));
  Assert.assertFalse(Modifier.isStatic(alpha.getModifiers()));
  Assert.assertFalse(Modifier.isStatic(abstractAlpha.getModifiers()));
  Assert.assertFalse(Modifier.isStatic(alphaImpl.getModifiers()));
}
