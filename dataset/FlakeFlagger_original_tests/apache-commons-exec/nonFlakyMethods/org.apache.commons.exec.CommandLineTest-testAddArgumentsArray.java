public void testAddArgumentsArray(){
  CommandLine cmdl=new CommandLine("test");
  cmdl.addArguments(new String[]{"foo","bar"});
  assertEquals("test foo bar",cmdl.toString());
  assertEquals(new String[]{"test","foo","bar"},cmdl.toStrings());
}
