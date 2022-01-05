@Test public void testSupplier() throws Exception {
  Exception[] exceptions=new Exception[3];
  for (int i=0; i < exceptions.length; ++i) {
    exceptions[i]=new Exception();
  }
  Object expected=new Object();
  ExceptionSupplier<Object,Exception> action=mock(ExceptionSupplier.class);
  Invoker invoker=new RetryingInvoker(Duration.ZERO,Duration.ofMillis(1));
  when(action.get()).thenReturn(expected);
  Object result=invoker.invoke(action);
  assertSame(expected,result);
  when(action.get()).thenThrow(exceptions[0]).thenReturn(expected);
  result=invoker.invoke(action);
  assertSame(expected,result);
  when(action.get()).thenThrow(exceptions[0],exceptions[1]).thenReturn(expected);
  result=invoker.invoke(action);
  assertSame(expected,result);
  when(action.get()).thenThrow(exceptions).thenReturn(expected);
  try {
    result=invoker.invoke(action);
    fail("Expected exception");
  }
 catch (  Exception e) {
    assertSame(exceptions[2],e);
  }
}
