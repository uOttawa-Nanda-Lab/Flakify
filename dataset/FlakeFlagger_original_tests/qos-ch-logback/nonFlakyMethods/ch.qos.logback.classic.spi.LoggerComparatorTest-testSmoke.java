@Test public void testSmoke(){
  assertEquals(0,comparator.compare(a,a));
  assertEquals(-1,comparator.compare(a,b));
  assertEquals(1,comparator.compare(b,a));
  assertEquals(-1,comparator.compare(root,a));
  assertEquals(1,comparator.compare(a,root));
  assertEquals(0,comparator.compare(root,root));
}
