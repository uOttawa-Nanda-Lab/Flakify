@Test public void streamIdHasReservedBit() throws IOException {
  Http20Draft09.Writer writer=new Http20Draft09.Writer(new OkBuffer(),true);
  try {
    int streamId=3;
    streamId|=1L << 31;
    writer.frameHeader(16383,Http20Draft09.TYPE_DATA,Http20Draft09.FLAG_NONE,streamId);
    fail();
  }
 catch (  IllegalArgumentException e) {
    assertEquals("reserved bit set: -2147483645",e.getMessage());
  }
}
