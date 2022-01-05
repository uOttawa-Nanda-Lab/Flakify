@Test public void testExecuteRunnable(){
  ServiceExecutor executor=new StampedLockServiceExecutor();
  Runnable executeTask=mock(Runnable.class);
  executor.execute(executeTask);
  verify(executeTask).run();
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
