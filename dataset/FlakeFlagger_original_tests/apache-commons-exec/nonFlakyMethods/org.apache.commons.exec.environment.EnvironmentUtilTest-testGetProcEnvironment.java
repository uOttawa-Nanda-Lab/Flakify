/** 
 * Test to access the environment variables of the current process.
 */
public void testGetProcEnvironment() throws IOException {
  Map procEnvironment=EnvironmentUtil.getProcEnvironment();
  assertTrue(procEnvironment.size() > 0);
  String[] envArgs=EnvironmentUtil.toStrings(procEnvironment);
  for (int i=0; i < envArgs.length; i++) {
    assertNotNull(envArgs[i]);
    assertTrue(envArgs[i].length() > 0);
    System.out.println(envArgs[i]);
  }
}
