@Test public void userAgent(){
  Request request=fromArgs("-A","foo","http://example.com").createRequest();
  assertEquals("GET",request.method());
  assertEquals("http://example.com",request.urlString());
  assertEquals("foo",request.header("User-Agent"));
  assertNull(request.body());
}
