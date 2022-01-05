@Test public void getAddress() throws UnknownHostException {
  InetAddress expected=InetAddress.getLocalHost();
  NetworkInterfaceBinding interfaceBinding=new NetworkInterfaceBinding(Collections.emptySet(),expected);
  SocketBindingManager bindingManager=mock(SocketBindingManager.class);
  SocketBinding binding=new SocketBinding("socket",1,true,null,0,interfaceBinding,bindingManager,Collections.emptyList());
  when(this.listener.getSocketBinding()).thenReturn(binding);
  InetAddress result=this.connector.getAddress();
  assertSame(expected,result);
}
