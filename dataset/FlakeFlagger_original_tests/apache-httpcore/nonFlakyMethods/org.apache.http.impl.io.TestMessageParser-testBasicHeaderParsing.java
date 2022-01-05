@Test public void testBasicHeaderParsing() throws Exception {
  String s="header1: stuff\r\n" + "header2  : stuff \r\n" + "header3: stuff\r\n"+ "     and more stuff\r\n"+ "\t and even more stuff\r\n"+ "     \r\n"+ "\r\n";
  SessionInputBuffer receiver=new SessionInputBufferMock(s,"US-ASCII");
  Header[] headers=AbstractMessageParser.parseHeaders(receiver,-1,-1,null);
  Assert.assertNotNull(headers);
  Assert.assertEquals(3,headers.length);
  Assert.assertEquals("header1",headers[0].getName());
  Assert.assertEquals("stuff",headers[0].getValue());
  Assert.assertEquals("header2",headers[1].getName());
  Assert.assertEquals("stuff",headers[1].getValue());
  Assert.assertEquals("header3",headers[2].getName());
  Assert.assertEquals("stuff and more stuff and even more stuff",headers[2].getValue());
  Header h=headers[0];
  Assert.assertTrue(h instanceof BufferedHeader);
  Assert.assertNotNull(((BufferedHeader)h).getBuffer());
  Assert.assertEquals("header1: stuff",((BufferedHeader)h).toString());
  Assert.assertEquals(8,((BufferedHeader)h).getValuePos());
}
