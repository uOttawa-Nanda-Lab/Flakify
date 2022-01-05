@Test public void getResponseCodeNoResponseBody() throws Exception {
  server.enqueue(new MockResponse().addHeader("abc: def"));
  server.play();
  URL url=server.getUrl("/");
  HttpURLConnection conn=client.open(url);
  conn.setDoInput(false);
  assertEquals("def",conn.getHeaderField("abc"));
  assertEquals(200,conn.getResponseCode());
  try {
    conn.getInputStream();
    fail();
  }
 catch (  ProtocolException expected) {
  }
}
