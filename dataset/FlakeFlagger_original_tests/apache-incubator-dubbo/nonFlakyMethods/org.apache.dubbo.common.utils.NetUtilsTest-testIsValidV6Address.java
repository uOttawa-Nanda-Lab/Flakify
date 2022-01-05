@Test public void testIsValidV6Address(){
  String saved=System.getProperty("java.net.preferIPv6Addresses","false");
  System.setProperty("java.net.preferIPv6Addresses","true");
  InetAddress address=NetUtils.getLocalAddress();
  if (address instanceof Inet6Address) {
    assertThat(NetUtils.isValidV6Address((Inet6Address)address),equalTo(true));
  }
  System.setProperty("java.net.preferIPv6Addresses",saved);
}
