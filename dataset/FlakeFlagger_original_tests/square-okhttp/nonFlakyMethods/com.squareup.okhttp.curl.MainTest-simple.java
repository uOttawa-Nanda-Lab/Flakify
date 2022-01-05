@Test public void simple(){
  Request request=fromArgs("http://example.com").createRequest();
  assertEquals("GET",request.method());
  assertEquals("http://example.com",request.urlString());
  assertNull(request.body());
}
