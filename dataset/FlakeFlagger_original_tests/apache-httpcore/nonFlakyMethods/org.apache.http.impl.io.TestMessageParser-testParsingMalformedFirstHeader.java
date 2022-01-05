@Test public void testParsingMalformedFirstHeader() throws Exception {
  String s="    header1: stuff\r\n" + "header2  : stuff \r\n";
  SessionInputBuffer receiver=new SessionInputBufferMock(s,"US-ASCII");
  Header[] headers=AbstractMessageParser.parseHeaders(receiver,-1,-1,null);
  Assert.assertNotNull(headers);
  Assert.assertEquals(2,headers.length);
  Assert.assertEquals("header1",headers[0].getName());
  Assert.assertEquals("stuff",headers[0].getValue());
  Assert.assertEquals("header2",headers[1].getName());
  Assert.assertEquals("stuff",headers[1].getValue());
}
