public void testAppendFailure(){
  appender.start();
  appender.queueSender=null;
  ILoggingEvent le=createLoggingEvent();
  for (int i=1; i <= 3; i++) {
    appender.append(le);
    assertEquals(i,context.getStatusManager().getCount());
    assertTrue(appender.isStarted());
  }
  appender.append(le);
  assertEquals(4,context.getStatusManager().getCount());
  assertFalse(appender.isStarted());
}
