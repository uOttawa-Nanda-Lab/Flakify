@Test public void closeSocket() throws IOException {
  Socket socket=mock(Socket.class);
  this.subject.close(socket);
  verify(socket).close();
}
