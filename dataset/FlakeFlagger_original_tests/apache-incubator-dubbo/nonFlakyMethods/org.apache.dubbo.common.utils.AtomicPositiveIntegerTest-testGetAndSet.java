@Test public void testGetAndSet() throws Exception {
  int get=i1.getAndSet(100);
  assertEquals(0,get);
  assertEquals(100,i1.get());
  try {
    i1.getAndSet(-1);
  }
 catch (  IllegalArgumentException expected) {
    assertThat(expected.getMessage(),allOf(containsString("new value"),containsString("< 0")));
  }
}
