@Test public void gzippedResponseBody() throws Exception {
  server.enqueue(new MockResponse().addHeader("Content-Encoding: gzip").setBody(gzip("ABCABCABC".getBytes(Util.UTF_8))));
  server.play();
  assertContent("ABCABCABC",client.open(server.getUrl("/r1")),Integer.MAX_VALUE);
}
