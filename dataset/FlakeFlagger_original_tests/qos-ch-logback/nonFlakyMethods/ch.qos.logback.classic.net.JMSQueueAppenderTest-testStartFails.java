public void testStartFails(){
  appender.queueBindingName=null;
  appender.start();
  assertFalse(appender.isStarted());
}
