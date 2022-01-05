@Test public void utf8() throws Exception {
  ByteString byteString=ByteString.encodeUtf8(bronzeHorseman);
  assertByteArraysEquals(byteString.toByteArray(),bronzeHorseman.getBytes(Util.UTF_8));
  assertTrue(byteString.equals(ByteString.of(bronzeHorseman.getBytes(Util.UTF_8))));
  assertEquals(byteString.utf8(),bronzeHorseman);
}
