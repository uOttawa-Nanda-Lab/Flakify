public void testParseWithOnlyWhitespace(){
  try {
    CommandLine.parse("  ");
    fail("IllegalArgumentException must be thrown due to incorrect command line");
  }
 catch (  IllegalArgumentException e) {
  }
}
