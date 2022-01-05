@Test public void extendendeThrowable() throws Exception {
  LoggingEvent event=createLoggingEvent();
  Throwable throwable=new Throwable("just testing");
  ThrowableProxy tp=new ThrowableProxy(throwable);
  event.setThrowableProxy(tp);
  tp.calculatePackagingData();
  ILoggingEvent remoteEvent=writeAndRead(event);
  checkForEquality(event,remoteEvent);
}
