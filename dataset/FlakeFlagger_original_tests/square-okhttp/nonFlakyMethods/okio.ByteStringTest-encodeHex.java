@Test public void encodeHex() throws Exception {
  assertEquals("000102",ByteString.of((byte)0x0,(byte)0x1,(byte)0x2).hex());
}
