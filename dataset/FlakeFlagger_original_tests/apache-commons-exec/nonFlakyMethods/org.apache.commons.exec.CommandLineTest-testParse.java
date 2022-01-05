public void testParse(){
  CommandLine cmdl=CommandLine.parse("test foo bar");
  assertEquals("test foo bar",cmdl.toString());
  assertEquals(new String[]{"test","foo","bar"},cmdl.toStrings());
}
