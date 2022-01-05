@Test public void equalsAscii() throws Exception {
  ByteString byteString=ByteString.encodeUtf8("Content-Length");
  assertTrue(byteString.equalsAscii("Content-Length"));
  assertFalse(byteString.equalsAscii("content-length"));
  assertFalse(ByteString.of((byte)0x63).equalsAscii(null));
  assertFalse(byteString.equalsAscii(bronzeHorseman));
  assertFalse(ByteString.encodeUtf8("Content-Length").equalsAscii("content-length"));
}
