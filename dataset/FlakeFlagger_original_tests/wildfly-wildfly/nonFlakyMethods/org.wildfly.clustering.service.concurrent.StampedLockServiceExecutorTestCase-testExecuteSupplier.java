@SuppressWarnings("unchecked") @Test public void testExecuteSupplier(){
  ServiceExecutor executor=new StampedLockServiceExecutor();
  Object expected=new Object();
  Supplier<Object> executeTask=mock(Supplier.class);
  when(executeTask.get()).thenReturn(expected);
  Optional<Object> result=executor.execute(executeTask);
  assertTrue(result.isPresent());
  assertSame(expected,result.get());
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
