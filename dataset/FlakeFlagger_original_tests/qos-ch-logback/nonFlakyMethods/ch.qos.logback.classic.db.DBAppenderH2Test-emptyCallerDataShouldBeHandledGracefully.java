@Test public void emptyCallerDataShouldBeHandledGracefully(){
  LoggingEvent event=createLoggingEvent();
  event.setCallerData(new StackTraceElement[0]);
  appender.append(event);
  event.setCallerData(new StackTraceElement[]{null});
  appender.append(event);
  checker.assertIsErrorFree();
}
