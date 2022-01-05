@Test public void toNameValueBlock(){
  Request request=new Request.Builder().url("http://square.com/").header("cache-control","no-cache, no-store").addHeader("set-cookie","Cookie1").addHeader("set-cookie","Cookie2").header(":status","200 OK").build();
  List<Header> headerBlock=SpdyTransport.writeNameValueBlock(request,Protocol.SPDY_3,"HTTP/1.1");
  List<Header> expected=headerEntries(":method","GET",":path","/",":version","HTTP/1.1",":host","square.com",":scheme","http","cache-control","no-cache, no-store","set-cookie","Cookie1\u0000Cookie2",":status","200 OK");
  assertEquals(expected,headerBlock);
}
