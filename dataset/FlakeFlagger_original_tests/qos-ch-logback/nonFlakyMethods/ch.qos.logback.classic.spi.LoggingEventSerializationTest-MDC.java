@Test public void MDC() throws Exception {
  MDC.put("key","testValue");
  ILoggingEvent event=createLoggingEvent();
  ILoggingEvent remoteEvent=writeAndRead(event);
  checkForEquality(event,remoteEvent);
  Map<String,String> MDCPropertyMap=remoteEvent.getMDCPropertyMap();
  assertEquals("testValue",MDCPropertyMap.get("key"));
}
