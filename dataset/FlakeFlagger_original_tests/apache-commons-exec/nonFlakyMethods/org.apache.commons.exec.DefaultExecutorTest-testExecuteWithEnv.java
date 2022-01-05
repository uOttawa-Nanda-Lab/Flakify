public void testExecuteWithEnv() throws Exception {
  Map env=new HashMap();
  env.put("TEST_ENV_VAR","XYZ");
  CommandLine cl=new CommandLine(testScript);
  int exitValue=exec.execute(cl,env);
  assertEquals("FOO.XYZ.",baos.toString().trim());
  assertEquals(0,exitValue);
}
