@Test public void emptyHeaderName() throws IOException {
  hpackWriter.writeByteString(ByteString.encodeUtf8(""));
  assertBytes(0);
  assertEquals(ByteString.EMPTY,newReader(byteStream(0)).readByteString(true));
  assertEquals(ByteString.EMPTY,newReader(byteStream(0)).readByteString(false));
}
