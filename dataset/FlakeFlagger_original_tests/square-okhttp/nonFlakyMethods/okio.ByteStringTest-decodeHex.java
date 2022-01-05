@Test public void decodeHex() throws Exception {
  assertEquals(ByteString.of((byte)0x0,(byte)0x1,(byte)0x2),ByteString.decodeHex("000102"));
}
