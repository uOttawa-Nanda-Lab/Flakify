/** 
 * Try to start an non-existing application asynchronously which should result in an exception.
 */
public void testExecuteAsyncWithNonExistingApplication() throws Exception {
  CommandLine cl=new CommandLine(nonExistingTestScript);
  MockExecuteResultHandler handler=new MockExecuteResultHandler();
  exec.execute(cl,handler);
  Thread.sleep(2000);
  assertEquals(Executor.INVALID_EXITVALUE,handler.getExitValue());
  assertTrue(handler.getException() instanceof ExecuteException);
}
