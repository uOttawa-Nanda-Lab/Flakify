@Test public void gc(){
  Logger logger=loggerContext.getLogger("gc" + rand);
  logger.setLevel(Level.INFO);
  System.gc();
  java.util.logging.Logger julLogger=JULHelper.asJULLogger(logger);
  java.util.logging.Level julLevel=JULHelper.asJULLevel(Level.INFO);
  assertEquals(julLevel,julLogger.getLevel());
}
