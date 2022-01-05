public void testParseWithUnevenQuotes(){
  try {
    CommandLine.parse("test \"foo bar");
    fail("IllegalArgumentException must be thrown due to uneven quotes");
  }
 catch (  IllegalArgumentException e) {
  }
}
