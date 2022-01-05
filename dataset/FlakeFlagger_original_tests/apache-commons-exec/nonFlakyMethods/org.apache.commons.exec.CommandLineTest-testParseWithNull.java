public void testParseWithNull(){
  try {
    CommandLine.parse(null);
    fail("IllegalArgumentException must be thrown due to incorrect command line");
  }
 catch (  IllegalArgumentException e) {
  }
}
