/** 
 * Start any processes in a loop to make sure that we do not leave any handles/resources open.
 */
public void testExecuteStability() throws Exception {
  for (int i=0; i < 1000; i++) {
    Map env=new HashMap();
    env.put("TEST_ENV_VAR",new Integer(i));
    CommandLine cl=new CommandLine(testScript);
    int exitValue=exec.execute(cl,env);
    assertEquals(0,exitValue);
    assertEquals("FOO." + i + ".",baos.toString().trim());
    baos.reset();
  }
}
