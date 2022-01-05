@Test public void testDecrementAndGet() throws Exception {
  int get=i1.decrementAndGet();
  assertEquals(Integer.MAX_VALUE,get);
  assertEquals(Integer.MAX_VALUE,i1.get());
  get=i2.decrementAndGet();
  assertEquals(126,get);
  assertEquals(126,i2.get());
  get=i3.decrementAndGet();
  assertEquals(Integer.MAX_VALUE - 1,get);
  assertEquals(Integer.MAX_VALUE - 1,i3.get());
}
