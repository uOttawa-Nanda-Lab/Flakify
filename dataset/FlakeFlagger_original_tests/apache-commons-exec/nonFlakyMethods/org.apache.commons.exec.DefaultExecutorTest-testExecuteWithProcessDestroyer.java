/** 
 * Test the proper handling of ProcessDestroyer for an synchronous process.
 */
public void testExecuteWithProcessDestroyer() throws Exception {
  CommandLine cl=new CommandLine(testScript);
  ShutdownHookProcessDestroyer processDestroyer=new ShutdownHookProcessDestroyer();
  exec.setProcessDestroyer(processDestroyer);
  assertTrue(processDestroyer.size() == 0);
  assertTrue(processDestroyer.isAddedAsShutdownHook() == false);
  int exitValue=exec.execute(cl);
  assertEquals("FOO..",baos.toString().trim());
  assertEquals(0,exitValue);
  assertTrue(processDestroyer.size() == 0);
  assertTrue(processDestroyer.isAddedAsShutdownHook() == false);
}
