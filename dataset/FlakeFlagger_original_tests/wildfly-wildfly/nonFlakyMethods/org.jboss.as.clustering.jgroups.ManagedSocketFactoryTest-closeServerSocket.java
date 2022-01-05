@Test public void closeServerSocket() throws IOException {
  ServerSocket socket=mock(ServerSocket.class);
  this.subject.close(socket);
  verify(socket).close();
}
