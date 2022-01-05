@Test public void testStickyForceCheck(){
  int count=testSticky(null,true);
  Assert.assertTrue(count == 0 || count == runs);
}
