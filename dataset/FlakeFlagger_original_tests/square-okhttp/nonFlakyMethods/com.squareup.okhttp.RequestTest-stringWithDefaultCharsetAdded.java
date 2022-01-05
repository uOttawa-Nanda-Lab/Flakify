@Test public void stringWithDefaultCharsetAdded() throws Exception {
  MediaType contentType=MediaType.parse("text/plain");
  Request.Body body=Request.Body.create(contentType,"\u0800");
  assertEquals(MediaType.parse("text/plain; charset=utf-8"),body.contentType());
  assertEquals(3,body.contentLength());
  assertEquals("e0a080",bodyToHex(body));
}
