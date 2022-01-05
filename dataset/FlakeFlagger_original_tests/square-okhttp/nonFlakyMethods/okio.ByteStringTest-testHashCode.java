@Test public void testHashCode() throws Exception {
  ByteString byteString=ByteString.decodeHex("0102");
  assertEquals(byteString.hashCode(),byteString.hashCode());
  assertEquals(byteString.hashCode(),ByteString.decodeHex("0102").hashCode());
}
