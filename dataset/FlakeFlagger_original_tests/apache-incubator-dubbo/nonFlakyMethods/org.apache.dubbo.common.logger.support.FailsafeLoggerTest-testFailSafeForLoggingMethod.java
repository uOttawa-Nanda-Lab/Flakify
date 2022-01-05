@Test public void testFailSafeForLoggingMethod(){
  Logger failLogger=mock(Logger.class);
  FailsafeLogger failsafeLogger=new FailsafeLogger(failLogger);
  doThrow(new RuntimeException()).when(failLogger).error(anyString());
  doThrow(new RuntimeException()).when(failLogger).warn(anyString());
  doThrow(new RuntimeException()).when(failLogger).info(anyString());
  doThrow(new RuntimeException()).when(failLogger).debug(anyString());
  doThrow(new RuntimeException()).when(failLogger).trace(anyString());
  failsafeLogger.error("error");
  failsafeLogger.warn("warn");
  failsafeLogger.info("info");
  failsafeLogger.debug("debug");
  failsafeLogger.trace("info");
  doThrow(new RuntimeException()).when(failLogger).error(any(Throwable.class));
  doThrow(new RuntimeException()).when(failLogger).warn(any(Throwable.class));
  doThrow(new RuntimeException()).when(failLogger).info(any(Throwable.class));
  doThrow(new RuntimeException()).when(failLogger).debug(any(Throwable.class));
  doThrow(new RuntimeException()).when(failLogger).trace(any(Throwable.class));
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
