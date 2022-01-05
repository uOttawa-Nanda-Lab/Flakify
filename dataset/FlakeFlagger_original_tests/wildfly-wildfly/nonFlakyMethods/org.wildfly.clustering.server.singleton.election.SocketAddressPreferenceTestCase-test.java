@Test public void test() throws UnknownHostException {
  InetSocketAddress preferredAddress=new InetSocketAddress(InetAddress.getByName("127.0.0.1"),1);
  InetSocketAddress otherAddress1=new InetSocketAddress(InetAddress.getByName("127.0.0.1"),2);
  InetSocketAddress otherAddress2=new InetSocketAddress(InetAddress.getByName("127.0.0.2"),1);
  Preference preference=new SocketAddressPreference(preferredAddress);
  Node preferredNode=mock(Node.class);
  Node otherNode1=mock(Node.class);
  Node otherNode2=mock(Node.class);
  when(preferredNode.getSocketAddress()).thenReturn(preferredAddress);
  when(otherNode1.getSocketAddress()).thenReturn(otherAddress1);
  when(otherNode2.getSocketAddress()).thenReturn(otherAddress2);
  assertTrue(preference.preferred(preferredNode));
  assertFalse(preference.preferred(otherNode1));
  assertFalse(preference.preferred(otherNode2));
}
