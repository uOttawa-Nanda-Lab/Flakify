public void testExecuteWithArg() throws Exception {
  CommandLine cl=new CommandLine(testScript);
  cl.addArgument("BAR");
  int exitValue=exec.execute(cl);
  assertEquals("FOO..BAR",baos.toString().trim());
  assertEquals(0,exitValue);
}
