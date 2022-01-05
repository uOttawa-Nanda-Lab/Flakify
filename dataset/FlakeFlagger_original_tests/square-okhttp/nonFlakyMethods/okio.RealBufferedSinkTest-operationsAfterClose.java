@Test public void operationsAfterClose() throws IOException {
  MockSink mockSink=new MockSink();
  BufferedSink bufferedSink=new RealBufferedSink(mockSink);
  bufferedSink.writeByte('a');
  bufferedSink.close();
  try {
    bufferedSink.writeByte('a');
    fail();
  }
 catch (  IllegalStateException expected) {
  }
  try {
    bufferedSink.write(new byte[10]);
    fail();
  }
 catch (  IllegalStateException expected) {
  }
  try {
    bufferedSink.emitCompleteSegments();
    fail();
  }
 catch (  IllegalStateException expected) {
  }
  try {
    bufferedSink.flush();
    fail();
  }
 catch (  IllegalStateException expected) {
  }
  OutputStream os=bufferedSink.outputStream();
  try {
    os.write('a');
    fail();
  }
 catch (  IOException expected) {
  }
  try {
    os.write(new byte[10]);
    fail();
  }
 catch (  IOException expected) {
  }
  os.flush();
}
