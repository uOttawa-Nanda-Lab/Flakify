public void testExecuteAsyncWithError() throws Exception {
  CommandLine cl=new CommandLine(errorTestScript);
  MockExecuteResultHandler handler=new MockExecuteResultHandler();
  exec.execute(cl,handler);
  Thread.sleep(2000);
  assertEquals(1,handler.getExitValue());
  assertTrue(handler.getException() instanceof ExecuteException);
  assertEquals("FOO..",baos.toString().trim());
}
