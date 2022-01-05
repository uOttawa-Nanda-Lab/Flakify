@Test public void testNoStart(){
  Appender<E> appender=getAppender();
  appender.setContext(context);
  appender.setName("doh");
  appender.doAppend(null);
  StatusChecker checker=new StatusChecker(context.getStatusManager());
  StatusPrinter.print(context);
  checker.assertContainsMatch("Attempted to append to non started appender \\[doh\\].");
}
