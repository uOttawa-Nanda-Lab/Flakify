@Test public void stringWithNonDefaultCharsetSpecified() throws Exception {
  MediaType contentType=MediaType.parse("text/plain; charset=utf-16be");
  Request.Body body=Request.Body.create(contentType,"\u0800");
  assertEquals(contentType,body.contentType());
  assertEquals(2,body.contentLength());
  assertEquals("0800",bodyToHex(body));
}
