private void createSocket(String serviceName,String bindingName) throws IOException {
  ManagedSocketFactory factory=mock(ManagedSocketFactory.class);
  Socket socket1=mock(Socket.class);
  Socket socket2=mock(Socket.class);
  Socket socket3=mock(Socket.class);
  Socket socket4=mock(Socket.class);
  Socket socket5=mock(Socket.class);
  InetAddress localhost=InetAddress.getLocalHost();
  when(this.manager.getSocketFactory()).thenReturn(factory);
  when(factory.createSocket(bindingName)).thenReturn(socket1);
  when(factory.createSocket(bindingName,localhost,1)).thenReturn(socket2);
  when(factory.createSocket(bindingName,"host",1)).thenReturn(socket3);
  when(factory.createSocket(bindingName,localhost,1,localhost,2)).thenReturn(socket4);
  when(factory.createSocket(bindingName,"host",1,localhost,2)).thenReturn(socket5);
  Socket result1=this.subject.createSocket(serviceName);
  Socket result2=this.subject.createSocket(serviceName,localhost,1);
  Socket result3=this.subject.createSocket(serviceName,"host",1);
  Socket result4=this.subject.createSocket(serviceName,localhost,1,localhost,2);
  Socket result5=this.subject.createSocket(serviceName,"host",1,localhost,2);
  assertSame(socket1,result1);
  assertSame(socket2,result2);
  assertSame(socket3,result3);
  assertSame(socket4,result4);
  assertSame(socket5,result5);
}
