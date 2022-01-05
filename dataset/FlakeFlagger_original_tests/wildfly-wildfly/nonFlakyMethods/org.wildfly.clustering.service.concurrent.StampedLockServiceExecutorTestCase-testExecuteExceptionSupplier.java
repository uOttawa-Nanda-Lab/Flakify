@SuppressWarnings("unchecked") @Test public void testExecuteExceptionSupplier() throws Exception {
  ServiceExecutor executor=new StampedLockServiceExecutor();
  Object expected=new Object();
  ExceptionSupplier<Object,Exception> executeTask=mock(ExceptionSupplier.class);
  when(executeTask.get()).thenReturn(expected);
  Optional<Object> result=executor.execute(executeTask);
  assertTrue(result.isPresent());
  assertSame(expected,result.get());
  reset(executeTask);
  doThrow(new Exception()).when(executeTask).get();
  try {
    executor.execute(executeTask);
    fail("Should have thrown an exception");
  }
 catch (  Exception e) {
    assertNotNull(e);
  }
  reset(executeTask);
  Runnable closeTask=mock(Runnable.class);
  executor.close(closeTask);
  verify(closeTask).run();
  reset(closeTask);
  executor.close(closeTask);
  verify(closeTask,never()).run();
  result=executor.execute(executeTask);
  assertFalse(result.isPresent());
}
