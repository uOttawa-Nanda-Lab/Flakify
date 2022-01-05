@Test public void put(){
  Request request=fromArgs("-X","PUT","http://example.com").createRequest();
  assertEquals("PUT",request.method());
  assertEquals("http://example.com",request.urlString());
  assertNull(request.body());
}
