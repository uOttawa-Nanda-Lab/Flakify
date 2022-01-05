@Test public void operationsAfterClose() throws IOException {
  OkBuffer source=new OkBuffer();
  BufferedSource bufferedSource=new RealBufferedSource(source);
  bufferedSource.close();
  try {
    bufferedSource.seek((byte)1);
    fail();
  }
 catch (  IllegalStateException expected) {
  }
  try {
    bufferedSource.skip(1);
    fail();
  }
 catch (  IllegalStateException expected) {
  }
  try {
    bufferedSource.readByte();
    fail();
  }
 catch (  IllegalStateException expected) {
  }
  try {
    bufferedSource.readByteString(10);
    fail();
  }
 catch (  IllegalStateException expected) {
  }
  InputStream is=bufferedSource.inputStream();
  try {
    is.read();
    fail();
  }
 catch (  IOException expected) {
  }
  try {
    is.read(new byte[10]);
    fail();
  }
 catch (  IOException expected) {
  }
}
