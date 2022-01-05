@Test public void closeMulticastSocket(){
  MulticastSocket socket=mock(MulticastSocket.class);
  this.subject.close(socket);
  verify(socket).close();
}
