public void testAddArgumentsArrayNull(){
  CommandLine cmdl=new CommandLine("test");
  cmdl.addArguments((String[])null);
  assertEquals("test",cmdl.toString());
  assertEquals(new String[]{"test"},cmdl.toStrings());
}
