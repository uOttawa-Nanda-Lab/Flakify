@Test public void testGetLocalSocketAddress() throws Exception {
  InetSocketAddress address=NetUtils.getLocalSocketAddress("localhost",12345);
  assertTrue(address.getAddress().isAnyLocalAddress());
  assertEquals(address.getPort(),12345);
  address=NetUtils.getLocalSocketAddress("dubbo-addr",12345);
  assertEquals(address.getHostName(),"dubbo-addr");
  assertEquals(address.getPort(),12345);
}
