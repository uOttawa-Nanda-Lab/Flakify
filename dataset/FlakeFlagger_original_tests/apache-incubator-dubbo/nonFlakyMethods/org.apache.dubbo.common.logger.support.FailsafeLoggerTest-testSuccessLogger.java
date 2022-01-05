@Test public void testSuccessLogger(){
  Logger successLogger=mock(Logger.class);
  FailsafeLogger failsafeLogger=new FailsafeLogger(successLogger);
  failsafeLogger.error("error");
  failsafeLogger.warn("warn");
  failsafeLogger.info("info");
  failsafeLogger.debug("debug");
  failsafeLogger.trace("info");
  verify(successLogger).error(anyString());
  verify(successLogger).warn(anyString());
  verify(successLogger).info(anyString());
  verify(successLogger).debug(anyString());
  verify(successLogger).trace(anyString());
  failsafeLogger.error(new Exception("error"));
  failsafeLogger.warn(new Exception("warn"));
  failsafeLogger.info(new Exception("info"));
  failsafeLogger.debug(new Exception("debug"));
  failsafeLogger.trace(new Exception("trace"));
  failsafeLogger.error("error",new Exception("error"));
  failsafeLogger.warn("warn",new Exception("warn"));
  failsafeLogger.info("info",new Exception("info"));
  failsafeLogger.debug("debug",new Exception("debug"));
  failsafeLogger.trace("trace",new Exception("trace"));
}
