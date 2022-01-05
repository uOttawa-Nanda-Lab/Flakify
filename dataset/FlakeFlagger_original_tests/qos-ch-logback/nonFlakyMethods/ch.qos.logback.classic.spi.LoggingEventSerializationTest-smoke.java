@Test public void smoke() throws Exception {
  ILoggingEvent event=createLoggingEvent();
  ILoggingEvent remoteEvent=writeAndRead(event);
  checkForEquality(event,remoteEvent);
}
