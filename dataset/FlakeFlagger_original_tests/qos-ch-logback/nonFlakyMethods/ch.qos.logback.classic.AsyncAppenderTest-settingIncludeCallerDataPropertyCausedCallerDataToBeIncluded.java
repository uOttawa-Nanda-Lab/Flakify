@Test public void settingIncludeCallerDataPropertyCausedCallerDataToBeIncluded(){
  asyncAppender.addAppender(listAppender);
  asyncAppender.setIncludeCallerData(true);
  asyncAppender.start();
  asyncAppender.doAppend(builder.build(diff));
  asyncAppender.stop();
  assertEquals(1,listAppender.list.size());
  ILoggingEvent e=listAppender.list.get(0);
  assertTrue(e.hasCallerData());
  StackTraceElement ste=e.getCallerData()[0];
  assertEquals(thisClassName,ste.getClassName());
}
