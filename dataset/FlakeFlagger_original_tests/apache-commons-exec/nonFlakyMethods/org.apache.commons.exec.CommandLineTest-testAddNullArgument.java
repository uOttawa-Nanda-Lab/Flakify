public void testAddNullArgument(){
  CommandLine cmdl=new CommandLine("test");
  cmdl.addArgument(null);
  assertEquals("test",cmdl.toString());
  assertEquals(new String[]{"test"},cmdl.toStrings());
}
