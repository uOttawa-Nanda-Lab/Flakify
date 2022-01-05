@Test public void testNewAppender(){
  Appender<E> appender=getAppender();
  assertFalse(appender.isStarted());
}
