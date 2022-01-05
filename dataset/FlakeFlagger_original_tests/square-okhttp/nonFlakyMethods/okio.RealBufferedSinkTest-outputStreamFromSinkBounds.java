@Test public void outputStreamFromSinkBounds() throws Exception {
  OkBuffer sink=new OkBuffer();
  OutputStream out=new RealBufferedSink(sink).outputStream();
  try {
    out.write(new byte[100],50,51);
    fail();
  }
 catch (  ArrayIndexOutOfBoundsException expected) {
  }
}
