@Test public void contentTypeHeader(){
  Request request=fromArgs("-d","foo","-H","Content-Type: application/json","http://example.com").createRequest();
  Request.Body body=request.body();
  assertEquals("POST",request.method());
  assertEquals("http://example.com",request.urlString());
  assertEquals("application/json; charset=utf-8",body.contentType().toString());
  assertEquals("foo",bodyAsString(body));
}
