@Test public void testIsLocalHost() throws Exception {
  assertTrue(NetUtils.isLocalHost("localhost"));
  assertTrue(NetUtils.isLocalHost("127.1.2.3"));
  assertFalse(NetUtils.isLocalHost("128.1.2.3"));
}
