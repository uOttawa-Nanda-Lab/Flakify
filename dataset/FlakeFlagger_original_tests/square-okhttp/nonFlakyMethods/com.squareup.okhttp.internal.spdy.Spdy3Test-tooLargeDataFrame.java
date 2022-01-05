@Test public void tooLargeDataFrame() throws IOException {
  try {
    sendDataFrame(new OkBuffer().write(new byte[0x1000000]));
    fail();
  }
 catch (  IllegalArgumentException e) {
    assertEquals("FRAME_TOO_LARGE max size is 16Mib: " + 0x1000000L,e.getMessage());
  }
}
