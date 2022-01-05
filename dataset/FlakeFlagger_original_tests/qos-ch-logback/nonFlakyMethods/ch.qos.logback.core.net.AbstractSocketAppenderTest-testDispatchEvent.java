@Test public void testDispatchEvent() throws Exception {
  ServerSocket serverSocket=ServerSocketUtil.createServerSocket();
  instrumentedAppender.setRemoteHost(serverSocket.getInetAddress().getHostAddress());
  instrumentedAppender.setPort(serverSocket.getLocalPort());
  instrumentedAppender.setQueueSize(1);
  instrumentedAppender.start();
  Socket appenderSocket=serverSocket.accept();
  serverSocket.close();
  instrumentedAppender.append("some event");
  final int shortDelay=100;
  for (int i=0, retries=DELAY / shortDelay; !instrumentedAppender.lastQueue.isEmpty() && i < retries; i++) {
    Thread.sleep(shortDelay);
  }
  assertTrue(instrumentedAppender.lastQueue.isEmpty());
  ObjectInputStream ois=new ObjectInputStream(appenderSocket.getInputStream());
  assertEquals("some event",ois.readObject());
  appenderSocket.close();
}
