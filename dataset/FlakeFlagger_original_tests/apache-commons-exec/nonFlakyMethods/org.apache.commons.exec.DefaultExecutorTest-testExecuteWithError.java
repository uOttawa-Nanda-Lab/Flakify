public void testExecuteWithError() throws Exception {
  CommandLine cl=new CommandLine(errorTestScript);
  try {
    exec.execute(cl);
    fail("Must throw ExecuteException");
  }
 catch (  ExecuteException e) {
    assertEquals(1,e.getExitValue());
  }
}
