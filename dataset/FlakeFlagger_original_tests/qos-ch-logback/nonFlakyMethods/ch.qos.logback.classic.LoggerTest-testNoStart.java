@Test public void testNoStart(){
  listAppender.setContext(lc);
  root.addAppender(listAppender);
  Logger logger=lc.getLogger(LoggerTest.class);
  logger.debug("hello");
  List<Status> statusList=lc.getStatusManager().getCopyOfStatusList();
  Status s0=statusList.get(0);
  assertEquals(Status.WARN,s0.getLevel());
  assertTrue(s0.getMessage().startsWith("Attempted to append to non started"));
}
