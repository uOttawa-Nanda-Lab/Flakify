@Test public void redirect() throws Exception {
  server.enqueue(new MockResponse().setResponseCode(301).addHeader("Location: /b").addHeader("Test","Redirect from /a to /b").setBody("/a has moved!"));
  server.enqueue(new MockResponse().setResponseCode(302).addHeader("Location: /c").addHeader("Test","Redirect from /b to /c").setBody("/b has moved!"));
  server.enqueue(new MockResponse().setBody("C"));
  server.play();
  onSuccess(new Request.Builder().url(server.getUrl("/a")).build()).assertCode(200).assertBody("C").redirectedBy().assertCode(302).assertContainsHeaders("Test: Redirect from /b to /c").redirectedBy().assertCode(301).assertContainsHeaders("Test: Redirect from /a to /b");
  assertEquals(0,server.takeRequest().getSequenceNumber());
  assertEquals(1,server.takeRequest().getSequenceNumber());
  assertEquals(2,server.takeRequest().getSequenceNumber());
}
