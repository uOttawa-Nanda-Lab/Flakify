@Test public void testFormattingTwoArg(){
  Logger logger=lc.getLogger(Logger.ROOT_LOGGER_NAME);
  logger.debug("{}-{}",new Integer(12),new Integer(13));
  ILoggingEvent event=(ILoggingEvent)listAppender.list.get(0);
  assertEquals("12-13",event.getFormattedMessage());
}
