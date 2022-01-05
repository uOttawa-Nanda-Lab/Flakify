@Test public void testMaxHeaderCount() throws Exception {
  String s="header1: stuff\r\n" + "header2: stuff \r\n" + "header3: stuff\r\n"+ "\r\n";
  SessionInputBuffer receiver=new SessionInputBufferMock(s,"US-ASCII");
  try {
    AbstractMessageParser.parseHeaders(receiver,2,-1,null);
    Assert.fail("IOException should have been thrown");
  }
 catch (  IOException ex) {
  }
}
