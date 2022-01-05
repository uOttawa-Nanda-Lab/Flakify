@Test public void testWithEnabledLevel() throws Exception {
  logger.setLevel(Level.DEBUG);
  client.run();
  client.close();
  ILoggingEvent rcvdEvent=appender.getLastEvent();
  assertEquals(event.getLoggerName(),rcvdEvent.getLoggerName());
  assertEquals(event.getLevel(),rcvdEvent.getLevel());
  assertEquals(event.getMessage(),rcvdEvent.getMessage());
}
