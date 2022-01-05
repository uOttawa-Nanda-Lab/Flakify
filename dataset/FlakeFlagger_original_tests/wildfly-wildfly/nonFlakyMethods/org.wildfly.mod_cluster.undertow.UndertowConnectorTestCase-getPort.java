@Test public void getPort() throws UnknownHostException {
  int expected=10;
  NetworkInterfaceBinding interfaceBinding=new NetworkInterfaceBinding(Collections.emptySet(),InetAddress.getLocalHost());
  SocketBindingManager bindingManager=mock(SocketBindingManager.class);
  SocketBinding binding=new SocketBinding("socket",expected,true,null,0,interfaceBinding,bindingManager,Collections.emptyList());
  when(this.listener.getSocketBinding()).thenReturn(binding);
  int result=this.connector.getPort();
  assertSame(expected,result);
}
