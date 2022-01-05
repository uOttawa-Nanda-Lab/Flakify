@Test public void serializeLargeArgs() throws Exception {
  StringBuffer buffer=new StringBuffer();
  for (int i=0; i < 100000; i++) {
    buffer.append("X");
  }
  String largeString=buffer.toString();
  Object[] argArray=new Object[]{new LuckyCharms(2),largeString};
  LoggingEvent event=createLoggingEvent();
  event.setArgumentArray(argArray);
  ILoggingEvent remoteEvent=writeAndRead(event);
  checkForEquality(event,remoteEvent);
  Object[] aa=remoteEvent.getArgumentArray();
  assertNotNull(aa);
  assertEquals(2,aa.length);
  String stringBack=(String)aa[1];
  assertEquals(largeString,stringBack);
}
