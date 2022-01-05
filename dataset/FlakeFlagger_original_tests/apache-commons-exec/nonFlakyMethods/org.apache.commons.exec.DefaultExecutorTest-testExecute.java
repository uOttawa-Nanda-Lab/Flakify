public void testExecute() throws Exception {
  CommandLine cl=new CommandLine(testScript);
  int exitValue=exec.execute(cl);
  assertEquals("FOO..",baos.toString().trim());
  assertEquals(0,exitValue);
}
