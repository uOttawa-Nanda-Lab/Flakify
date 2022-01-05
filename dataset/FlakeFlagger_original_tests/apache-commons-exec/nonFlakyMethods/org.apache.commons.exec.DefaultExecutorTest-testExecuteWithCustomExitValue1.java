/** 
 * Invoke the error script but define that "1" is a good exit value and therefore no exception should be thrown.
 */
public void testExecuteWithCustomExitValue1() throws Exception {
  exec.setExitValue(1);
  CommandLine cl=new CommandLine(errorTestScript);
  exec.execute(cl);
}
