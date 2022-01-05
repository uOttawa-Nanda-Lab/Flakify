@Test public void testServerDropsConnection() throws Exception {
  receiver.setRemoteHost(InetAddress.getLocalHost().getHostName());
  receiver.setPort(6000);
  receiver.start();
  assertTrue(receiver.awaitConnectorCreated(DELAY));
  Socket socket=serverSocket.accept();
  socket.close();
}
