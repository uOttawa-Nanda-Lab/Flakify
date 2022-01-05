@Test public void _Throwable() throws Exception {
  LoggingEvent event=createLoggingEvent();
  Throwable throwable=new Throwable("just testing");
  ThrowableProxy tp=new ThrowableProxy(throwable);
  event.setThrowableProxy(tp);
  ILoggingEvent remoteEvent=writeAndRead(event);
  checkForEquality(event,remoteEvent);
}
