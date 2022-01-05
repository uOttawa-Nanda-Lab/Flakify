@Test public void equals() throws Exception {
  ByteString byteString=ByteString.decodeHex("000102");
  assertTrue(byteString.equals(byteString));
  assertTrue(byteString.equals(ByteString.decodeHex("000102")));
  assertTrue(ByteString.of().equals(ByteString.EMPTY));
  assertTrue(ByteString.EMPTY.equals(ByteString.of()));
  assertFalse(byteString.equals(new Object()));
  assertFalse(byteString.equals(ByteString.decodeHex("000201")));
}
