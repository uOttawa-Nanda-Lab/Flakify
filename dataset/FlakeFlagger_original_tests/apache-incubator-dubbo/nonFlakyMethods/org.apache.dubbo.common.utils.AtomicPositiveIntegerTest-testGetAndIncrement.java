@Test public void testGetAndIncrement() throws Exception {
  int get=i1.getAndIncrement();
  assertEquals(0,get);
  assertEquals(1,i1.get());
  get=i2.getAndIncrement();
  assertEquals(127,get);
  assertEquals(128,i2.get());
  get=i3.getAndIncrement();
  assertEquals(Integer.MAX_VALUE,get);
  assertEquals(0,i3.get());
}
