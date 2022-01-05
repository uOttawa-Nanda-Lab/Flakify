public void testAddArgumentWithSingleQuote(){
  CommandLine cmdl=new CommandLine("test");
  cmdl.addArgument("foo");
  cmdl.addArgument("ba'r");
  assertEquals("test foo \"ba'r\"",cmdl.toString());
  assertEquals(new String[]{"test","foo","\"ba\'r\""},cmdl.toStrings());
}
