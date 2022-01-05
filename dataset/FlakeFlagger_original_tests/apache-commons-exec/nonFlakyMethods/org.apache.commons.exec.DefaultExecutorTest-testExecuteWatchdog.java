/** 
 * Start a scipt looping forever and check if the ExecuteWatchdog kicks in killing the run away process.
 */
public void testExecuteWatchdog() throws Exception {
  long timeout=10000;
  CommandLine cl=new CommandLine(foreverTestScript);
  DefaultExecutor executor=new DefaultExecutor();
  executor.setWorkingDirectory(new File("."));
  ExecuteWatchdog watchdog=new ExecuteWatchdog(timeout);
  executor.setWatchdog(watchdog);
  try {
    executor.execute(cl);
  }
 catch (  ExecuteException e) {
    assertTrue(executor.getWatchdog().killedProcess());
    return;
  }
catch (  Throwable t) {
    fail(t.getMessage());
  }
  fail("Process was not killed");
}
