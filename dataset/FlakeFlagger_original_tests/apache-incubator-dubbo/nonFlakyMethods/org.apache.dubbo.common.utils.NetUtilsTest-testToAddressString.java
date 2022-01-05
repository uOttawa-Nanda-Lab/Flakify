@Test public void testToAddressString() throws Exception {
  InetAddress address=mock(InetAddress.class);
  when(address.getHostAddress()).thenReturn("dubbo");
  InetSocketAddress socketAddress=new InetSocketAddress(address,1234);
  assertThat(NetUtils.toAddressString(socketAddress),equalTo("dubbo:1234"));
}
