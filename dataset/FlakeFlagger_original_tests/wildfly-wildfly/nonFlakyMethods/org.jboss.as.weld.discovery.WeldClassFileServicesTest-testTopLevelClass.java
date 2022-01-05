@Test public void testTopLevelClass(){
  Assert.assertTrue(alpha.isTopLevelClass());
  Assert.assertTrue(alpha.isTopLevelClass());
  Assert.assertTrue(alpha.isTopLevelClass());
  Assert.assertFalse(innerInterface.isTopLevelClass());
}
