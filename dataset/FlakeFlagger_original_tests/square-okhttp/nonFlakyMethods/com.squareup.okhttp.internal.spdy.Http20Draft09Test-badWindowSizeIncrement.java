@Test public void badWindowSizeIncrement() throws IOException {
  try {
    windowUpdate(0);
    fail();
  }
 catch (  IllegalArgumentException e) {
    assertEquals("windowSizeIncrement == 0 || windowSizeIncrement > 0x7fffffffL: 0",e.getMessage());
  }
  try {
    windowUpdate(0x80000000L);
    fail();
  }
 catch (  IllegalArgumentException e) {
    assertEquals("windowSizeIncrement == 0 || windowSizeIncrement > 0x7fffffffL: 2147483648",e.getMessage());
  }
}
