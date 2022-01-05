public void testAddArguments(){
  CommandLine cmdl=new CommandLine("test");
  cmdl.addArguments("foo bar");
  assertEquals("test foo bar",cmdl.toString());
  assertEquals(new String[]{"test","foo","bar"},cmdl.toStrings());
}
