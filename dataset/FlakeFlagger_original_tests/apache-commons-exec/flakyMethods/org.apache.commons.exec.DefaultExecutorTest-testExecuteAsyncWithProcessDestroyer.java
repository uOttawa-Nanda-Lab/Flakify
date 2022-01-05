/** 
 * Test the proper handling of ProcessDestroyer for an asynchronous process. Since we do not terminate the process it will be terminated in the ShutdownHookProcessDestroyer implementation
 */
public void testExecuteAsyncWithProcessDestroyer() throws Exception {
  CommandLine cl=new CommandLine(foreverTestScript);
  MockExecuteResultHandler handler=new MockExecuteResultHandler();
  ShutdownHookProcessDestroyer processDestroyer=new ShutdownHookProcessDestroyer();
  ExecuteWatchdog watchdog=new ExecuteWatchdog(Integer.MAX_VALUE);
  assertTrue(exec.getProcessDestroyer() == null);
  assertTrue(processDestroyer.size() == 0);
  assertTrue(processDestroyer.isAddedAsShutdownHook() == false);
  exec.setWatchdog(watchdog);
  exec.setProcessDestroyer(processDestroyer);
  exec.execute(cl,handler);
  Thread.sleep(2000);
  assertNotNull(exec.getProcessDestroyer());
  assertTrue(processDestroyer.size() == 1);
  assertTrue(processDestroyer.isAddedAsShutdownHook() == true);
  watchdog.destroyProcess();
  assertTrue(watchdog.killedProcess());
  Thread.sleep(100);
  assertTrue(processDestroyer.size() == 0);
  assertTrue(processDestroyer.isAddedAsShutdownHook() == false);
}
