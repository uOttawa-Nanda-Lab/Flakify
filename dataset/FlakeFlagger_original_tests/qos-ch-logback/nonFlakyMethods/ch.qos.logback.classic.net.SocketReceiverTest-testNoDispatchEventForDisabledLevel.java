@Test public void testNoDispatchEventForDisabledLevel() throws Exception {
  receiver.setRemoteHost(InetAddress.getLocalHost().getHostName());
  receiver.setPort(6000);
  receiver.start();
  assertTrue(receiver.awaitConnectorCreated(DELAY));
  Socket socket=serverSocket.accept();
  ObjectOutputStream oos=new ObjectOutputStream(socket.getOutputStream());
  logger.setLevel(Level.INFO);
  ILoggingEvent event=new LoggingEvent(logger.getName(),logger,Level.DEBUG,"test message",null,new Object[0]);
  LoggingEventVO eventVO=LoggingEventVO.build(event);
  oos.writeObject(eventVO);
  oos.flush();
  assertNull(appender.awaitAppend(DELAY));
}
