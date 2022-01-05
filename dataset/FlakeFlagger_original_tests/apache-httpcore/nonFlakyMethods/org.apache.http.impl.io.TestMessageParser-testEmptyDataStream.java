@Test public void testEmptyDataStream() throws Exception {
  String s="";
  SessionInputBuffer receiver=new SessionInputBufferMock(s,"US-ASCII");
  Header[] headers=AbstractMessageParser.parseHeaders(receiver,-1,-1,null);
  Assert.assertNotNull(headers);
  Assert.assertEquals(0,headers.length);
}
