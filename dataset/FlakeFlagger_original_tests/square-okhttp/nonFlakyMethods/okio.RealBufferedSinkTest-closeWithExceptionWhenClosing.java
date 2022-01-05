@Test public void closeWithExceptionWhenClosing() throws IOException {
  MockSink mockSink=new MockSink();
  mockSink.scheduleThrow(1,new IOException());
  BufferedSink bufferedSink=new RealBufferedSink(mockSink);
  bufferedSink.writeByte('a');
  try {
    bufferedSink.close();
    fail();
  }
 catch (  IOException expected) {
  }
  mockSink.assertLog("write(OkBuffer[size=1 data=61], 1)","close()");
}
