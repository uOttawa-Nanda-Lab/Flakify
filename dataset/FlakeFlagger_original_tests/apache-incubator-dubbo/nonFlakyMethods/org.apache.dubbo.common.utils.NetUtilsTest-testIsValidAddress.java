@Test public void testIsValidAddress() throws Exception {
  assertFalse(NetUtils.isValidAddress((InetAddress)null));
  InetAddress address=mock(InetAddress.class);
  when(address.isLoopbackAddress()).thenReturn(true);
  assertFalse(NetUtils.isValidAddress(address));
  address=mock(InetAddress.class);
  when(address.getHostAddress()).thenReturn("localhost");
  assertFalse(NetUtils.isValidAddress(address));
  address=mock(InetAddress.class);
  when(address.getHostAddress()).thenReturn("0.0.0.0");
  assertFalse(NetUtils.isValidAddress(address));
  address=mock(InetAddress.class);
  when(address.getHostAddress()).thenReturn("127.0.0.1");
  assertFalse(NetUtils.isValidAddress(address));
  address=mock(InetAddress.class);
  when(address.getHostAddress()).thenReturn("1.2.3.4");
  assertTrue(NetUtils.isValidAddress(address));
}
