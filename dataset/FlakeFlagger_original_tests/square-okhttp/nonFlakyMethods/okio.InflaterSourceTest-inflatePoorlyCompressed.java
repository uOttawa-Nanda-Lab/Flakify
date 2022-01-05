@Test public void inflatePoorlyCompressed() throws Exception {
  ByteString original=randomBytes(1024 * 1024);
  OkBuffer deflated=deflate(original);
  OkBuffer inflated=inflate(deflated);
  assertEquals(original,inflated.readByteString(inflated.size()));
}
