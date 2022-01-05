@Test public void skipInsufficientData() throws Exception {
  OkBuffer source=new OkBuffer();
  source.writeUtf8("a");
  BufferedSource bufferedSource=new RealBufferedSource(source);
  try {
    bufferedSource.skip(2);
    fail();
  }
 catch (  EOFException expected) {
  }
}
