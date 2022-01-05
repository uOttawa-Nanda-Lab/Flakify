@Test public void byteArray() throws Exception {
  MediaType contentType=MediaType.parse("text/plain");
  Request.Body body=Request.Body.create(contentType,"abc".getBytes(Util.UTF_8));
  assertEquals(contentType,body.contentType());
  assertEquals(3,body.contentLength());
  assertEquals("616263",bodyToHex(body));
  assertEquals("Retransmit body","616263",bodyToHex(body));
}
