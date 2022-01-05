@Test public void badWindowSizeIncrement() throws IOException {
  try {
    windowUpdate(0);
    fail();
  }
 catch (  IllegalArgumentException e) {
    assertEquals("windowSizeIncrement must be between 1 and 0x7fffffff: 0",e.getMessage());
  }
  try {
    windowUpdate(0x80000000L);
    fail();
  }
 catch (  IllegalArgumentException e) {
    assertEquals("windowSizeIncrement must be between 1 and 0x7fffffff: 2147483648",e.getMessage());
  }
}
