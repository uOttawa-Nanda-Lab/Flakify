@Test public void context() throws Exception {
  lc.putProperty("testKey","testValue");
  ILoggingEvent event=createLoggingEvent();
  ILoggingEvent remoteEvent=writeAndRead(event);
  checkForEquality(event,remoteEvent);
  assertNotNull(remoteEvent.getLoggerName());
  assertEquals(Logger.ROOT_LOGGER_NAME,remoteEvent.getLoggerName());
  LoggerContextVO loggerContextRemoteView=remoteEvent.getLoggerContextVO();
  assertNotNull(loggerContextRemoteView);
  assertEquals("testContext",loggerContextRemoteView.getName());
  Map<String,String> props=loggerContextRemoteView.getPropertyMap();
  assertNotNull(props);
  assertEquals("testValue",props.get("testKey"));
}
