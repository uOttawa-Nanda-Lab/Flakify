@Test public void testRLParseFailure() throws Exception {
  try {
    BasicLineParser.parseRequestLine("    ",null);
    Assert.fail();
  }
 catch (  ParseException e) {
  }
  try {
    BasicLineParser.parseRequestLine("  GET",null);
    Assert.fail();
  }
 catch (  ParseException e) {
  }
  try {
    BasicLineParser.parseRequestLine("GET /stuff",null);
    Assert.fail();
  }
 catch (  ParseException e) {
  }
  try {
    BasicLineParser.parseRequestLine("GET/stuff HTTP/1.1",null);
    Assert.fail();
  }
 catch (  ParseException e) {
  }
  try {
    BasicLineParser.parseRequestLine("GET /stuff HTTP/1.1 Oooooooooooppsie",null);
    Assert.fail();
  }
 catch (  ParseException e) {
  }
}
