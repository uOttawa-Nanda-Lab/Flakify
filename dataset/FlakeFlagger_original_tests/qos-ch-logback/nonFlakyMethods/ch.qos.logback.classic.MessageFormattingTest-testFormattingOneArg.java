@Test public void testFormattingOneArg(){
  Logger logger=lc.getLogger(Logger.ROOT_LOGGER_NAME);
  logger.debug("{}",new Integer(12));
  ILoggingEvent event=(ILoggingEvent)listAppender.list.get(0);
  assertEquals("12",event.getFormattedMessage());
}
