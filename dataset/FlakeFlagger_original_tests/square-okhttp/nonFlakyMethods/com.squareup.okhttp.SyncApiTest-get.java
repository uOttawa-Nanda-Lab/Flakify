@Test public void get() throws Exception {
  server.enqueue(new MockResponse().setBody("abc").addHeader("Content-Type: text/plain"));
  server.play();
  Request request=new Request.Builder().url(server.getUrl("/")).header("User-Agent","SyncApiTest").build();
  onSuccess(request).assertCode(200).assertContainsHeaders("Content-Type: text/plain").assertBody("abc");
  assertTrue(server.takeRequest().getHeaders().contains("User-Agent: SyncApiTest"));
}
