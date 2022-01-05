@Test public void headerName() throws IOException {
  hpackWriter.writeByteString(ByteString.encodeUtf8("foo"));
  assertBytes(3,'f','o','o');
  assertEquals("foo",newReader(byteStream(3,'F','o','o')).readByteString(true).utf8());
}
