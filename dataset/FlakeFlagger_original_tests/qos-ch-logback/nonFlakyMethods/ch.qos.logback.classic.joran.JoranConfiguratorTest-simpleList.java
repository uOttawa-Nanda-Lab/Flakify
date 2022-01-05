@Test public void simpleList() throws JoranException {
  configure(ClassicTestConstants.JORAN_INPUT_PREFIX + "simpleList.xml");
  Logger logger=loggerContext.getLogger(this.getClass().getName());
  Logger root=loggerContext.getLogger(Logger.ROOT_LOGGER_NAME);
  ListAppender listAppender=(ListAppender)root.getAppender("LIST");
  assertEquals(0,listAppender.list.size());
  String msg="hello world";
  logger.debug(msg);
  assertEquals(1,listAppender.list.size());
  ILoggingEvent le=(ILoggingEvent)listAppender.list.get(0);
  assertEquals(msg,le.getMessage());
}
