@Test public void gunzipExhaustsSource() throws Exception {
  OkBuffer gzippedSource=new OkBuffer().write(ByteString.decodeHex("1f8b08000000000000004b4c4a0600c241243503000000"));
  ExhaustableSource exhaustableSource=new ExhaustableSource(gzippedSource);
  BufferedSource gunzippedSource=Okio.buffer(new GzipSource(exhaustableSource));
  assertEquals('a',gunzippedSource.readByte());
  assertEquals('b',gunzippedSource.readByte());
  assertEquals('c',gunzippedSource.readByte());
  assertFalse(exhaustableSource.exhausted);
  assertEquals(-1,gunzippedSource.read(new OkBuffer(),1));
  assertTrue(exhaustableSource.exhausted);
}
