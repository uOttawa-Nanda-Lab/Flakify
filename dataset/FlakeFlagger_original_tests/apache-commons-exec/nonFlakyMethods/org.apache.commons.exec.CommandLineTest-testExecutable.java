public void testExecutable(){
  CommandLine cmdl=new CommandLine("test");
  assertEquals("test",cmdl.toString());
  assertEquals(new String[]{"test"},cmdl.toStrings());
  assertEquals("test",cmdl.getExecutable());
  assertTrue(cmdl.getArguments().length == 0);
}
