@Test public void testAllLogMethod(){
  logger.error("error");
  logger.warn("warn");
  logger.info("info");
  logger.debug("debug");
  logger.trace("info");
  logger.error(new Exception("error"));
  logger.warn(new Exception("warn"));
  logger.info(new Exception("info"));
  logger.debug(new Exception("debug"));
  logger.trace(new Exception("trace"));
  logger.error("error",new Exception("error"));
  logger.warn("warn",new Exception("warn"));
  logger.info("info",new Exception("info"));
  logger.debug("debug",new Exception("debug"));
  logger.trace("trace",new Exception("trace"));
}
