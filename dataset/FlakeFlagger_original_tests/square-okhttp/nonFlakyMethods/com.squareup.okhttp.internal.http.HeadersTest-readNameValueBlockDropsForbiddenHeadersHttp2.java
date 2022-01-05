@Test public void readNameValueBlockDropsForbiddenHeadersHttp2() throws IOException {
  List<Header> headerBlock=headerEntries(":status","200 OK",":version","HTTP/1.1","connection","close");
  Request request=new Request.Builder().url("http://square.com/").build();
  Response response=SpdyTransport.readNameValueBlock(headerBlock,Protocol.HTTP_2).request(request).build();
  Headers headers=response.headers();
  assertEquals(1,headers.size());
  assertEquals(OkHeaders.SELECTED_PROTOCOL,headers.name(0));
  assertEquals(Protocol.HTTP_2.name.utf8(),headers.value(0));
}
