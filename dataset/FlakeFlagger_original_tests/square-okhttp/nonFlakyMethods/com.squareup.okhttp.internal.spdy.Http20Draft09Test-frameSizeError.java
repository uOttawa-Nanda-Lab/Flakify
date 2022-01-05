@Test public void frameSizeError() throws IOException {
  Http20Draft09.Writer writer=new Http20Draft09.Writer(new OkBuffer(),true);
  try {
    writer.frameHeader(16384,Http20Draft09.TYPE_DATA,Http20Draft09.FLAG_NONE,0);
    fail();
  }
 catch (  IllegalArgumentException e) {
    assertEquals("FRAME_SIZE_ERROR length > 16383: 16384",e.getMessage());
  }
}
