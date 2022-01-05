@Test public void toNameValueBlockDropsForbiddenHeadersSpdy3(){
  Request request=new Request.Builder().url("http://square.com/").header("Connection","close").header("Transfer-Encoding","chunked").build();
  List<Header> expected=headerEntries(":method","GET",":path","/",":version","HTTP/1.1",":host","square.com",":scheme","http");
  assertEquals(expected,SpdyTransport.writeNameValueBlock(request,Protocol.SPDY_3,"HTTP/1.1"));
}
