@Test public void testParsingInvalidHeaders() throws Exception {
  String s="    stuff\r\n" + "header1: stuff\r\n" + "\r\n";
  SessionInputBuffer receiver=new SessionInputBufferMock(s,"US-ASCII");
  try {
    AbstractMessageParser.parseHeaders(receiver,-1,-1,null);
    Assert.fail("ProtocolException should have been thrown");
  }
 catch (  ProtocolException ex) {
  }
  s="  :  stuff\r\n" + "header1: stuff\r\n" + "\r\n";
  receiver=new SessionInputBufferMock(s,"US-ASCII");
  try {
    AbstractMessageParser.parseHeaders(receiver,-1,-1,null);
    Assert.fail("ProtocolException should have been thrown");
  }
 catch (  ProtocolException ex) {
  }
}
