@Test public void testIsInvalidLocalHost() throws Exception {
  assertTrue(NetUtils.isInvalidLocalHost(null));
  assertTrue(NetUtils.isInvalidLocalHost(""));
  assertTrue(NetUtils.isInvalidLocalHost("localhost"));
  assertTrue(NetUtils.isInvalidLocalHost("0.0.0.0"));
  assertTrue(NetUtils.isInvalidLocalHost("127.1.2.3"));
}
