@Test public void testInvalidHttpVersionParsing() throws Exception {
  try {
    BasicLineParser.parseProtocolVersion((String)null,null);
    Assert.fail("IllegalArgumentException should have been thrown");
  }
 catch (  IllegalArgumentException e) {
  }
  try {
    BasicLineParser.parseProtocolVersion("    ",null);
    Assert.fail("ParseException should have been thrown");
  }
 catch (  ParseException e) {
  }
  try {
    BasicLineParser.parseProtocolVersion("HTT",null);
    Assert.fail("ParseException should have been thrown");
  }
 catch (  ParseException e) {
  }
  try {
    BasicLineParser.parseProtocolVersion("crap",null);
    Assert.fail("ParseException should have been thrown");
  }
 catch (  ParseException e) {
  }
  try {
    BasicLineParser.parseProtocolVersion("HTTP/crap",null);
    Assert.fail("ParseException should have been thrown");
  }
 catch (  ParseException e) {
  }
  try {
    BasicLineParser.parseProtocolVersion("HTTP/1",null);
    Assert.fail("ParseException should have been thrown");
  }
 catch (  ParseException e) {
  }
  try {
    BasicLineParser.parseProtocolVersion("HTTP/1234   ",null);
    Assert.fail("ParseException should have been thrown");
  }
 catch (  ParseException e) {
  }
  try {
    BasicLineParser.parseProtocolVersion("HTTP/1.",null);
    Assert.fail("ParseException should have been thrown");
  }
 catch (  ParseException e) {
  }
  try {
    BasicLineParser.parseProtocolVersion("HTTP/whatever.whatever whatever",null);
    Assert.fail("ParseException should have been thrown");
  }
 catch (  ParseException e) {
  }
  try {
    BasicLineParser.parseProtocolVersion("HTTP/1.whatever whatever",null);
    Assert.fail("ParseException should have been thrown");
  }
 catch (  ParseException e) {
  }
}
