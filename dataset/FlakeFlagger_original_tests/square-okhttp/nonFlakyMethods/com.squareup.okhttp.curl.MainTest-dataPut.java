@Test public void dataPut(){
  Request request=fromArgs("-d","foo","-X","PUT","http://example.com").createRequest();
  Request.Body body=request.body();
  assertEquals("PUT",request.method());
  assertEquals("http://example.com",request.urlString());
  assertEquals("application/x-form-urlencoded; charset=utf-8",body.contentType().toString());
  assertEquals("foo",bodyAsString(body));
}
