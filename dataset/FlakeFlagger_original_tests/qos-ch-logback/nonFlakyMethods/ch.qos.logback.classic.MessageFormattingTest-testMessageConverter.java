@Test public void testMessageConverter(){
  Logger logger=lc.getLogger(Logger.ROOT_LOGGER_NAME);
  logger.debug("{}",12);
  ILoggingEvent event=(ILoggingEvent)listAppender.list.get(0);
  PatternLayout layout=new PatternLayout();
  layout.setContext(lc);
  layout.setPattern("%m");
  layout.start();
  String formattedMessage=layout.doLayout(event);
  assertEquals("12",formattedMessage);
}
