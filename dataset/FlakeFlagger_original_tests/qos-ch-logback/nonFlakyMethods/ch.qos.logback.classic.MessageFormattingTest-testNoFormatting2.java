@Test public void testNoFormatting2(){
  Logger logger=lc.getLogger(Logger.ROOT_LOGGER_NAME);
  logger.debug("test");
  ILoggingEvent event=(ILoggingEvent)listAppender.list.get(0);
  assertEquals("test",event.getFormattedMessage());
}
