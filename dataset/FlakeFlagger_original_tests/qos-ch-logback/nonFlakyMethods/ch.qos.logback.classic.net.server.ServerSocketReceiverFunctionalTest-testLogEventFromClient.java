@Test public void testLogEventFromClient() throws Exception {
  ILoggingEvent event=new LoggingEvent(logger.getName(),logger,Level.DEBUG,"test message",null,new Object[0]);
  Socket socket=new Socket(InetAddress.getLocalHost(),serverSocket.getLocalPort());
  try {
    LoggingEventVO eventVO=LoggingEventVO.build(event);
    ObjectOutputStream oos=new ObjectOutputStream(socket.getOutputStream());
    for (int i=0; i < EVENT_COUNT; i++) {
      oos.writeObject(eventVO);
    }
    oos.writeObject(eventVO);
    oos.flush();
  }
  finally {
    socket.close();
  }
  ILoggingEvent rcvdEvent=appender.awaitAppend(SHUTDOWN_DELAY);
  assertNotNull(rcvdEvent);
  assertEquals(event.getLoggerName(),rcvdEvent.getLoggerName());
  assertEquals(event.getLevel(),rcvdEvent.getLevel());
  assertEquals(event.getMessage(),rcvdEvent.getMessage());
}
