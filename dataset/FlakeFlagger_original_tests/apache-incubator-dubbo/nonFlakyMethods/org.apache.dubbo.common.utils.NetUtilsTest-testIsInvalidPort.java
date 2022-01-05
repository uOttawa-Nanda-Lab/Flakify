@Test public void testIsInvalidPort() throws Exception {
  assertTrue(NetUtils.isInvalidPort(0));
  assertTrue(NetUtils.isInvalidPort(65536));
  assertFalse(NetUtils.isInvalidPort(1024));
}
