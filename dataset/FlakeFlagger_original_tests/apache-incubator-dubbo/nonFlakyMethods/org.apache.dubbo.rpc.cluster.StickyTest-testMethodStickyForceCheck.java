@Test public void testMethodStickyForceCheck(){
  int count=testSticky("method1",true);
  Assert.assertTrue(count == 0 || count == runs);
}
