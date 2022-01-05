@Test public void testIncrementAndGet() throws Exception {
  int get=i1.incrementAndGet();
  assertEquals(1,get);
  assertEquals(1,i1.get());
  get=i2.incrementAndGet();
  assertEquals(128,get);
  assertEquals(128,i2.get());
  get=i3.incrementAndGet();
  assertEquals(0,get);
  assertEquals(0,i3.get());
}
