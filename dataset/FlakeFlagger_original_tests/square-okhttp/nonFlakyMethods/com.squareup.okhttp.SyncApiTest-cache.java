@Test public void cache() throws Exception {
  server.enqueue(new MockResponse().setBody("A").addHeader("ETag: v1"));
  server.enqueue(new MockResponse().setResponseCode(HttpURLConnection.HTTP_NOT_MODIFIED));
  server.play();
  client.setOkResponseCache(cache);
  onSuccess(new Request.Builder().url(server.getUrl("/")).build()).assertCode(200).assertBody("A");
  assertNull(server.takeRequest().getHeader("If-None-Match"));
  onSuccess(new Request.Builder().url(server.getUrl("/")).build()).assertCode(200).assertBody("A");
  assertEquals("v1",server.takeRequest().getHeader("If-None-Match"));
}
