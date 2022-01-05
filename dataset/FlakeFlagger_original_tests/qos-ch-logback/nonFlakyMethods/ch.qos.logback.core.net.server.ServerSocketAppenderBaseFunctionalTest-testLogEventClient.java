@Test public void testLogEventClient() throws Exception {
  appender.start();
  Socket socket=new Socket(InetAddress.getLocalHost(),serverSocket.getLocalPort());
  socket.setSoTimeout(1000);
  ObjectInputStream ois=new ObjectInputStream(socket.getInputStream());
  for (int i=0; i < EVENT_COUNT; i++) {
    appender.append(TEST_EVENT + i);
    assertEquals(TEST_EVENT + i,ois.readObject());
  }
  socket.close();
  appender.stop();
}
