@Test public void testStartFails(){
  appender.topicBindingName=null;
  appender.start();
  assertFalse(appender.isStarted());
}
