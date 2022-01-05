@Test public void testBasicMessageParsing() throws Exception {
  String s="HTTP/1.1 200 OK\r\n" + "Server: whatever\r\n" + "Date: some date\r\n"+ "Set-Cookie: c1=stuff\r\n"+ "\r\n";
  SessionInputBuffer inbuffer=new SessionInputBufferMock(s,"US-ASCII");
  HttpResponseParser parser=new HttpResponseParser(inbuffer,BasicLineParser.DEFAULT,new DefaultHttpResponseFactory(),new BasicHttpParams());
  HttpResponse httpresponse=parser.parse();
  StatusLine statusline=httpresponse.getStatusLine();
  Assert.assertNotNull(statusline);
  Assert.assertEquals(200,statusline.getStatusCode());
  Assert.assertEquals("OK",statusline.getReasonPhrase());
  Assert.assertEquals(HttpVersion.HTTP_1_1,statusline.getProtocolVersion());
  Header[] headers=httpresponse.getAllHeaders();
  Assert.assertEquals(3,headers.length);
}
