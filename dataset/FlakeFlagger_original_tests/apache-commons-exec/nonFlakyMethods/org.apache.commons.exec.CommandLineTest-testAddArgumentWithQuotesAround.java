public void testAddArgumentWithQuotesAround(){
  CommandLine cmdl=new CommandLine("test");
  cmdl.addArgument("\'foo\'");
  cmdl.addArgument("\"bar\"");
  cmdl.addArgument("\"fe z\"");
  assertEquals("test foo bar \"fe z\"",cmdl.toString());
  assertEquals(new String[]{"test","foo","bar","\"fe z\""},cmdl.toStrings());
}
