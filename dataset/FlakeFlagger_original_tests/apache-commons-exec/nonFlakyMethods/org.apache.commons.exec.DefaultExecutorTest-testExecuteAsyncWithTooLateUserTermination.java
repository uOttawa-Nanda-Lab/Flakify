/** 
 * Start a async process and try to terminate it manually but the process was already terminated by the watchdog.
 */
public void testExecuteAsyncWithTooLateUserTermination() throws Exception {
  CommandLine cl=new CommandLine(foreverTestScript);
  ExecuteWatchdog watchdog=new ExecuteWatchdog(3000);
  exec.setWatchdog(watchdog);
  MockExecuteResultHandler handler=new MockExecuteResultHandler();
  exec.execute(cl,handler);
  Thread.sleep(6000);
  watchdog.destroyProcess();
  assertTrue(watchdog.killedProcess());
}
