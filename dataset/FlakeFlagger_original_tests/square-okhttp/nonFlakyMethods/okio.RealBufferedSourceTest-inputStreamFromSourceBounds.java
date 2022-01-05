@Test public void inputStreamFromSourceBounds() throws IOException {
  OkBuffer source=new OkBuffer();
  source.writeUtf8(repeat('a',100));
  InputStream in=new RealBufferedSource(source).inputStream();
  try {
    in.read(new byte[100],50,51);
    fail();
  }
 catch (  ArrayIndexOutOfBoundsException expected) {
  }
}
