/** 
 * Start a async process and terminate it manually before the wacthdog timeout occurs. 
 */
public void testExecuteAsyncWithTimelyUserTermination() throws Exception {
  CommandLine cl=new CommandLine(foreverTestScript);
  ExecuteWatchdog watchdog=new ExecuteWatchdog(Integer.MAX_VALUE);
  exec.setWatchdog(watchdog);
  MockExecuteResultHandler handler=new MockExecuteResultHandler();
  exec.execute(cl,handler);
  Thread.sleep(2000);
  watchdog.destroyProcess();
  assertTrue(watchdog.killedProcess());
}
