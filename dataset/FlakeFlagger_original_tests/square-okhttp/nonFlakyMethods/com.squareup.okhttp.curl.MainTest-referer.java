@Test public void referer(){
  Request request=fromArgs("-e","foo","http://example.com").createRequest();
  assertEquals("GET",request.method());
  assertEquals("http://example.com",request.urlString());
  assertEquals("foo",request.header("Referer"));
  assertNull(request.body());
}
