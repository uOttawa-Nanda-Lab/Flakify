@Test public void testGetAndAnd() throws Exception {
  int get=i1.getAndAdd(3);
  assertEquals(0,get);
  assertEquals(3,i1.get());
  get=i2.getAndAdd(3);
  assertEquals(127,get);
  assertEquals(127 + 3,i2.get());
  get=i3.getAndAdd(3);
  assertEquals(Integer.MAX_VALUE,get);
  assertEquals(2,i3.get());
}
