@Test public void testConfiguredAppender(){
  Appender<E> appender=getConfiguredAppender();
  appender.start();
  assertTrue(appender.isStarted());
  appender.stop();
  assertFalse(appender.isStarted());
}
