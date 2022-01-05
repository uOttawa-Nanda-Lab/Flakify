@Test public void tooLargeDataFrame() throws IOException {
  try {
    sendDataFrame(new OkBuffer().write(new byte[0x1000000]));
    fail();
  }
 catch (  IllegalArgumentException e) {
    assertEquals("FRAME_SIZE_ERROR length > 16383: 16777216",e.getMessage());
  }
}
