public void testParseWithQuotes(){
  CommandLine cmdl=CommandLine.parse("test \"foo\" \'ba r\'");
  assertEquals("test foo \"ba r\"",cmdl.toString());
  assertEquals(new String[]{"test","foo","\"ba r\""},cmdl.toStrings());
}
