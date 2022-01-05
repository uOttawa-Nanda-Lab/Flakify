@Test public void testAppendNonTriggeringEvent(){
  LoggingEvent event=new LoggingEvent();
  event.setThreadName("thead name");
  event.setLevel(Level.DEBUG);
  appender.subAppend(cb,event);
  assertEquals(1,cb.length());
}
