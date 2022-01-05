@Test public void testStatusWithUnconfiguredContext(){
  Logger logger=lc.getLogger(LoggerContextTest.class);
  for (int i=0; i < 3; i++) {
    logger.debug("test");
  }
  logger=lc.getLogger("x.y.z");
  for (int i=0; i < 3; i++) {
    logger.debug("test");
  }
  StatusManager sm=lc.getStatusManager();
  assertTrue("StatusManager has recieved too many messages",sm.getCount() == 1);
}
