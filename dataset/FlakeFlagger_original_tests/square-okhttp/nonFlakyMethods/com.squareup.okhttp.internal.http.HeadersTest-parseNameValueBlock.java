@Test public void parseNameValueBlock() throws IOException {
  List<Header> headerBlock=headerEntries("cache-control","no-cache, no-store","set-cookie","Cookie1\u0000Cookie2",":status","200 OK",":version","HTTP/1.1");
  Request request=new Request.Builder().url("http://square.com/").build();
  Response response=SpdyTransport.readNameValueBlock(headerBlock,Protocol.SPDY_3).request(request).build();
  Headers headers=response.headers();
  assertEquals(4,headers.size());
  assertEquals("HTTP/1.1 200 OK",response.statusLine());
  assertEquals("no-cache, no-store",headers.get("cache-control"));
  assertEquals("Cookie2",headers.get("set-cookie"));
  assertEquals(Protocol.SPDY_3.name.utf8(),headers.get(OkHeaders.SELECTED_PROTOCOL));
  assertEquals(OkHeaders.SELECTED_PROTOCOL,headers.name(0));
  assertEquals(Protocol.SPDY_3.name.utf8(),headers.value(0));
  assertEquals("cache-control",headers.name(1));
  assertEquals("no-cache, no-store",headers.value(1));
  assertEquals("set-cookie",headers.name(2));
  assertEquals("Cookie1",headers.value(2));
  assertEquals("set-cookie",headers.name(3));
  assertEquals("Cookie2",headers.value(3));
  assertNull(headers.get(":status"));
  assertNull(headers.get(":version"));
}
