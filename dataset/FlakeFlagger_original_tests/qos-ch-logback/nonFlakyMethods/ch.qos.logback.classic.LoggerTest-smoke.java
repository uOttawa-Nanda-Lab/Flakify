@Test public void smoke(){
  ListAppender<ILoggingEvent> listAppender=new ListAppender<ILoggingEvent>();
  listAppender.start();
  root.addAppender(listAppender);
  Logger logger=lc.getLogger(LoggerTest.class);
  assertEquals(0,listAppender.list.size());
  logger.debug("hello");
  assertEquals(1,listAppender.list.size());
}
