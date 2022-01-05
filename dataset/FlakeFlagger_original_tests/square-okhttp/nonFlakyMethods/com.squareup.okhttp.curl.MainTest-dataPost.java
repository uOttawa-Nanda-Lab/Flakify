@Test public void dataPost(){
  Request request=fromArgs("-d","foo","http://example.com").createRequest();
  Request.Body body=request.body();
  assertEquals("POST",request.method());
  assertEquals("http://example.com",request.urlString());
  assertEquals("application/x-form-urlencoded; charset=utf-8",body.contentType().toString());
  assertEquals("foo",bodyAsString(body));
}
