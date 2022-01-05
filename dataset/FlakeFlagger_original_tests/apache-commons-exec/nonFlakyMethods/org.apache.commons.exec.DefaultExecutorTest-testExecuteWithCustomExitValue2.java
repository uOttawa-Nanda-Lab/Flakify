/** 
 * Invoke the error script but define that "1" is a bad exit value and therefore no exception should be thrown.
 */
public void testExecuteWithCustomExitValue2() throws Exception {
  CommandLine cl=new CommandLine(errorTestScript);
  exec.setExitValue(2);
  try {
    exec.execute(cl);
    fail("Must throw ExecuteException");
  }
 catch (  ExecuteException e) {
    assertEquals(1,e.getExitValue());
    return;
  }
}
