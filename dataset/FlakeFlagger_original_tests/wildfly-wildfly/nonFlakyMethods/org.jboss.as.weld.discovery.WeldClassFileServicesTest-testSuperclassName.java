@Test public void testSuperclassName(){
  Assert.assertEquals(Object.class.getName(),alpha.getSuperclassName());
  Assert.assertEquals(Object.class.getName(),abstractAlpha.getSuperclassName());
  Assert.assertEquals(AbstractAlpha.class.getName(),alphaImpl.getSuperclassName());
}
