@SuppressWarnings("unchecked") @Test public void testExecuteExceptionRunnable() throws Exception {
  ServiceExecutor executor=new StampedLockServiceExecutor();
  ExceptionRunnable<Exception> executeTask=mock(ExceptionRunnable.class);
  executor.execute(executeTask);
  verify(executeTask).run();
  reset(executeTask);
  doThrow(new Exception()).when(executeTask).run();
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
  executor.execute(executeTask);
  verify(executeTask,never()).run();
}
