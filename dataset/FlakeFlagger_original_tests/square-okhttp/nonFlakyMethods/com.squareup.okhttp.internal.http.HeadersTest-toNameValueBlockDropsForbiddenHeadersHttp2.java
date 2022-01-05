@Test public void toNameValueBlockDropsForbiddenHeadersHttp2(){
  Request request=new Request.Builder().url("http://square.com/").header("Connection","upgrade").header("Upgrade","websocket").build();
  List<Header> expected=headerEntries(":method","GET",":path","/",":authority","square.com",":scheme","http");
  assertEquals(expected,SpdyTransport.writeNameValueBlock(request,Protocol.HTTP_2,"HTTP/1.1"));
}
