@Test public void testGetAndDecrement() throws Exception {
  int get=i1.getAndDecrement();
  assertEquals(0,get);
  assertEquals(Integer.MAX_VALUE,i1.get());
  get=i2.getAndDecrement();
  assertEquals(127,get);
  assertEquals(126,i2.get());
  get=i3.getAndDecrement();
  assertEquals(Integer.MAX_VALUE,get);
  assertEquals(Integer.MAX_VALUE - 1,i3.get());
}
