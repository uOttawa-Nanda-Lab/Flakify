public void testExecuteWithInvalidWorkingDirectory() throws Exception {
  File workingDir=new File("/foo/bar");
  CommandLine cl=new CommandLine(testScript);
  exec.setWorkingDirectory(workingDir);
  try {
    exec.execute(cl);
    fail("Expected exception due to invalid working directory");
  }
 catch (  IOException e) {
    return;
  }
}
