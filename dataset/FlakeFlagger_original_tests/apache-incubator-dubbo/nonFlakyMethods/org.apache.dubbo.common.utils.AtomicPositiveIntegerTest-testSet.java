@Test public void testSet() throws Exception {
  i1.set(100);
  assertEquals(100,i1.get());
  try {
    i1.set(-1);
    fail();
  }
 catch (  IllegalArgumentException expected) {
    assertThat(expected.getMessage(),allOf(containsString("new value"),containsString("< 0")));
  }
}
