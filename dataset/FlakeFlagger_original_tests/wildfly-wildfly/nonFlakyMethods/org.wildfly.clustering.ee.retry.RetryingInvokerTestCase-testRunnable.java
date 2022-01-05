@Test public void testRunnable() throws Exception {
  Exception[] exceptions=new Exception[3];
  for (int i=0; i < exceptions.length; ++i) {
    exceptions[i]=new Exception();
  }
  ExceptionRunnable<Exception> action=mock(ExceptionRunnable.class);
  doNothing().when(action).run();
  Invoker invoker=new RetryingInvoker(Duration.ZERO,Duration.ZERO);
  invoker.invoke(action);
  doThrow(exceptions[0]).doNothing().when(action).run();
  invoker.invoke(action);
  doThrow(exceptions[0],exceptions[1]).doNothing().when(action).run();
  invoker.invoke(action);
  doThrow(exceptions).doNothing().when(action).run();
  try {
    invoker.invoke(action);
    fail("Expected exception");
  }
 catch (  Exception e) {
    assertSame(exceptions[2],e);
  }
}
