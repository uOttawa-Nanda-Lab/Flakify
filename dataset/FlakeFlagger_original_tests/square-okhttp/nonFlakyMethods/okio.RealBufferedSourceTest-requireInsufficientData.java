@Test public void requireInsufficientData() throws Exception {
  OkBuffer source=new OkBuffer();
  source.writeUtf8("a");
  BufferedSource bufferedSource=new RealBufferedSource(source);
  try {
    bufferedSource.require(2);
    fail();
  }
 catch (  EOFException expected) {
  }
}
