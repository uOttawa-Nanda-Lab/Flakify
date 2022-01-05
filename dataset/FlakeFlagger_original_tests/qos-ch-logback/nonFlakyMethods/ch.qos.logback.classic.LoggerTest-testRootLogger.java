@Test public void testRootLogger(){
  Logger logger=(Logger)LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
  LoggerContext lc=logger.getLoggerContext();
  assertNotNull("Returned logger is null",logger);
  assertEquals("Return logger isn't named root",logger.getName(),Logger.ROOT_LOGGER_NAME);
  assertTrue("logger instances should be indentical",logger == lc.root);
}
