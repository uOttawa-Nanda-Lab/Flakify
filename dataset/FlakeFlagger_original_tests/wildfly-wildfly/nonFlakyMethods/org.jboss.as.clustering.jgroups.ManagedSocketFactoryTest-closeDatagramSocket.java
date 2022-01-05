@Test public void closeDatagramSocket(){
  DatagramSocket socket=mock(DatagramSocket.class);
  this.subject.close(socket);
  verify(socket).close();
}
