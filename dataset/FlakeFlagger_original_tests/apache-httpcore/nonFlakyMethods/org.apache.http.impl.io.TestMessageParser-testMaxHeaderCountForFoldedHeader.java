@Test public void testMaxHeaderCountForFoldedHeader() throws Exception {
  String s="header1: stuff\r\n" + " stuff \r\n" + " stuff\r\n"+ "\r\n";
  SessionInputBuffer receiver=new SessionInputBufferMock(s,"US-ASCII");
  try {
    AbstractMessageParser.parseHeaders(receiver,2,15,null);
    Assert.fail("IOException should have been thrown");
  }
 catch (  IOException ex) {
  }
}
