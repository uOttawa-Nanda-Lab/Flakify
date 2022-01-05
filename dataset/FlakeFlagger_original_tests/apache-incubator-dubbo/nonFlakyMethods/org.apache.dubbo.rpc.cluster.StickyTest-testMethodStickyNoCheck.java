@Test public void testMethodStickyNoCheck(){
  int count=testSticky("method1",false);
  System.out.println(count);
  Assert.assertTrue(count > 0 && count <= runs);
}
