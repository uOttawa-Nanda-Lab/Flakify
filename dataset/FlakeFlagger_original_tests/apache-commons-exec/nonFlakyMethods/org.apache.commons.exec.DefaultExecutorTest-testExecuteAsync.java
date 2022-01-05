public void testExecuteAsync() throws Exception {
  CommandLine cl=new CommandLine(testScript);
  MockExecuteResultHandler handler=new MockExecuteResultHandler();
  exec.execute(cl,handler);
  Thread.sleep(2000);
  assertEquals(0,handler.getExitValue());
  assertEquals("FOO..",baos.toString().trim());
}
