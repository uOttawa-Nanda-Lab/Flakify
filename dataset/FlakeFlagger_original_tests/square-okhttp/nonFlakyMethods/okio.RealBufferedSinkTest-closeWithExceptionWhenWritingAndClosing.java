@Test public void closeWithExceptionWhenWritingAndClosing() throws IOException {
  MockSink mockSink=new MockSink();
  mockSink.scheduleThrow(0,new IOException("first"));
  mockSink.scheduleThrow(1,new IOException("second"));
  BufferedSink bufferedSink=new RealBufferedSink(mockSink);
  bufferedSink.writeByte('a');
  try {
    bufferedSink.close();
    fail();
  }
 catch (  IOException expected) {
    assertEquals("first",expected.getMessage());
  }
  mockSink.assertLog("write(OkBuffer[size=1 data=61], 1)","close()");
}
