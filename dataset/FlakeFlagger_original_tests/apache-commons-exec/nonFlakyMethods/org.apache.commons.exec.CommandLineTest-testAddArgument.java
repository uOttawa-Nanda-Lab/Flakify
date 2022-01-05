public void testAddArgument(){
  CommandLine cmdl=new CommandLine("test");
  cmdl.addArgument("foo");
  cmdl.addArgument("bar");
  assertEquals("test foo bar",cmdl.toString());
  assertEquals(new String[]{"test","foo","bar"},cmdl.toStrings());
}
