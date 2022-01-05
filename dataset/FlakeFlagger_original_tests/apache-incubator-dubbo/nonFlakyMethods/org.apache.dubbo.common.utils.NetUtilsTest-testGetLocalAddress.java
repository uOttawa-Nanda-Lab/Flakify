@Test public void testGetLocalAddress() throws Exception {
  InetAddress address=NetUtils.getLocalAddress();
  assertNotNull(address);
  assertTrue(NetUtils.isValidLocalHost(address.getHostAddress()));
}
