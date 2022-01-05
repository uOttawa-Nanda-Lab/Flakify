@Test public void eventWasPreparedForDeferredProcessing(){
  asyncAppender.addAppender(listAppender);
  asyncAppender.start();
  String k="k" + diff;
  MDC.put(k,"v");
  asyncAppender.doAppend(builder.build(diff));
  MDC.clear();
  asyncAppender.stop();
  assertFalse(asyncAppender.isStarted());
  assertEquals(1,listAppender.list.size());
  ILoggingEvent e=listAppender.list.get(0);
  assertEquals("v",e.getMDCPropertyMap().get(k));
  assertFalse(e.hasCallerData());
}
