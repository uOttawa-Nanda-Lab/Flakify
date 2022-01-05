@Test public void testNoFormatting(){
  Logger logger=lc.getLogger(Logger.ROOT_LOGGER_NAME);
  logger.debug("test",new Integer(12),new Integer(13));
  ILoggingEvent event=(ILoggingEvent)listAppender.list.get(0);
  assertEquals("test",event.getFormattedMessage());
}
