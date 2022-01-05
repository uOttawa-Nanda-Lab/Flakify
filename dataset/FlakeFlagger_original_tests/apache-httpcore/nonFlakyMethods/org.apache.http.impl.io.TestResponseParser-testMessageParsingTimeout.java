@Test public void testMessageParsingTimeout() throws Exception {
  String s="HTTP\000/1.1 200\000 OK\r\n" + "Server: wha\000tever\r\n" + "Date: some date\r\n"+ "Set-Coo\000kie: c1=stuff\r\n"+ "\000\r\n";
  SessionInputBuffer inbuffer=new SessionInputBufferMock(new TimeoutByteArrayInputStream(s.getBytes("US-ASCII")),16);
  HttpResponseParser parser=new HttpResponseParser(inbuffer,BasicLineParser.DEFAULT,new DefaultHttpResponseFactory(),new BasicHttpParams());
  int timeoutCount=0;
  HttpResponse httpresponse=null;
  for (int i=0; i < 10; i++) {
    try {
      httpresponse=parser.parse();
      break;
    }
 catch (    InterruptedIOException ex) {
      timeoutCount++;
    }
  }
  Assert.assertNotNull(httpresponse);
  Assert.assertEquals(5,timeoutCount);
  @SuppressWarnings("null") StatusLine statusline=httpresponse.getStatusLine();
  Assert.assertNotNull(statusline);
  Assert.assertEquals(200,statusline.getStatusCode());
  Assert.assertEquals("OK",statusline.getReasonPhrase());
  Assert.assertEquals(HttpVersion.HTTP_1_1,statusline.getProtocolVersion());
  Header[] headers=httpresponse.getAllHeaders();
  Assert.assertEquals(3,headers.length);
}
