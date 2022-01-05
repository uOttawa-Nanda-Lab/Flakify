@Test public void testSLParseFailure() throws Exception {
  try {
    BasicLineParser.parseStatusLine("xxx 200 OK",null);
    Assert.fail();
  }
 catch (  ParseException e) {
  }
  try {
    BasicLineParser.parseStatusLine("HTTP/1.1 xxx OK",null);
    Assert.fail();
  }
 catch (  ParseException e) {
  }
  try {
    BasicLineParser.parseStatusLine("HTTP/1.1    ",null);
    Assert.fail();
  }
 catch (  ParseException e) {
  }
  try {
    BasicLineParser.parseStatusLine("HTTP/1.1",null);
    Assert.fail();
  }
 catch (  ParseException e) {
  }
  try {
    BasicLineParser.parseStatusLine("HTTP/1.1 -200 OK",null);
    Assert.fail();
  }
 catch (  ParseException e) {
  }
}
