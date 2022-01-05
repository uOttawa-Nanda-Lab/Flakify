public void testAddArgumentsWithQuotesAndSpaces(){
  CommandLine cmdl=new CommandLine("test");
  cmdl.addArguments("'fo o' \"ba r\"");
  assertEquals("test \"fo o\" \"ba r\"",cmdl.toString());
  assertEquals(new String[]{"test","\"fo o\"","\"ba r\""},cmdl.toStrings());
}
