@Test public void gunzipThrowsIfSourceIsNotExhausted() throws Exception {
  OkBuffer gzippedSource=new OkBuffer().write(ByteString.decodeHex("1f8b08000000000000004b4c4a0600c241243503000000"));
  gzippedSource.writeByte('d');
  BufferedSource gunzippedSource=Okio.buffer(new GzipSource(gzippedSource));
  assertEquals('a',gunzippedSource.readByte());
  assertEquals('b',gunzippedSource.readByte());
  assertEquals('c',gunzippedSource.readByte());
  try {
    gunzippedSource.readByte();
    fail();
  }
 catch (  IOException expected) {
  }
}
