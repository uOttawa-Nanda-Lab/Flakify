@Test public void testGet() throws Exception {
  assertEquals(0,i1.get());
  assertEquals(127,i2.get());
  assertEquals(Integer.MAX_VALUE,i3.get());
}
