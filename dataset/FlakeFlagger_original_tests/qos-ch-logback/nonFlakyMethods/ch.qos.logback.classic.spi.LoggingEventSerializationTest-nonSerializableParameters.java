@Test public void nonSerializableParameters() throws Exception {
  LoggingEvent event=createLoggingEvent();
  LuckyCharms lucky0=new LuckyCharms(0);
  event.setArgumentArray(new Object[]{lucky0,null});
  ILoggingEvent remoteEvent=writeAndRead(event);
  checkForEquality(event,remoteEvent);
  Object[] aa=remoteEvent.getArgumentArray();
  assertNotNull(aa);
  assertEquals(2,aa.length);
  assertEquals("LC(0)",aa[0]);
  assertNull(aa[1]);
}
