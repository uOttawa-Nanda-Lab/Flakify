public void testExecuteWithWorkingDirectory() throws Exception {
  File workingDir=new File(".");
  CommandLine cl=new CommandLine(testScript);
  exec.setWorkingDirectory(new File("."));
  int exitValue=exec.execute(cl);
  assertEquals("FOO..",baos.toString().trim());
  assertEquals(0,exitValue);
  assertEquals(exec.getWorkingDirectory(),workingDir);
}
