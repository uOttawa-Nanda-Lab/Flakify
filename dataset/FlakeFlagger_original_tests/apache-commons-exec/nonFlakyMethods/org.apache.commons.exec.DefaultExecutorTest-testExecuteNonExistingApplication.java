/** 
 * Try to start an non-existing application which should result in an exception.
 */
public void testExecuteNonExistingApplication() throws Exception {
  CommandLine cl=new CommandLine(nonExistingTestScript);
  DefaultExecutor executor=new DefaultExecutor();
  try {
    executor.execute(cl);
  }
 catch (  IOException e) {
    return;
  }
  fail("Got no exception when executing an non-existing application");
}
