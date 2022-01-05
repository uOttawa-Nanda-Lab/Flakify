@Test public void postBodyRetransmittedOnRedirect() throws Exception {
  server.enqueue(new MockResponse().setResponseCode(302).addHeader("Location: /b").setBody("Moved to /b !"));
  server.enqueue(new MockResponse().setBody("This is b."));
  server.play();
  Request request=new Request.Builder().url(server.getUrl("/")).post(Request.Body.create(MediaType.parse("text/plain"),"body!")).build();
  onSuccess(request).assertCode(200).assertBody("This is b.");
  RecordedRequest request1=server.takeRequest();
  assertEquals("body!",request1.getUtf8Body());
  assertEquals("5",request1.getHeader("Content-Length"));
  assertEquals("text/plain; charset=utf-8",request1.getHeader("Content-Type"));
  assertEquals(0,request1.getSequenceNumber());
  RecordedRequest request2=server.takeRequest();
  assertEquals("body!",request2.getUtf8Body());
  assertEquals("5",request2.getHeader("Content-Length"));
  assertEquals("text/plain; charset=utf-8",request2.getHeader("Content-Type"));
  assertEquals(1,request2.getSequenceNumber());
}
