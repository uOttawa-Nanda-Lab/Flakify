@Test public void testAddAndGet() throws Exception {
  int get=i1.addAndGet(3);
  assertEquals(3,get);
  assertEquals(3,i1.get());
  get=i2.addAndGet(3);
  assertEquals(127 + 3,get);
  assertEquals(127 + 3,i2.get());
  get=i3.addAndGet(3);
  assertEquals(2,get);
  assertEquals(2,i3.get());
}
