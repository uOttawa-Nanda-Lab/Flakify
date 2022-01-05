@Test public void redirectedPostStripsRequestBodyHeaders() throws Exception {
  server.enqueue(new MockResponse().setResponseCode(HttpURLConnection.HTTP_MOVED_TEMP).addHeader("Location: /page2"));
  server.enqueue(new MockResponse().setBody("Page 2"));
  server.play();
  connection=client.open(server.getUrl("/page1"));
  connection.setDoOutput(true);
  connection.addRequestProperty("Content-Length","4");
  connection.addRequestProperty("Content-Type","text/plain; charset=utf-8");
  connection.addRequestProperty("Transfer-Encoding","identity");
  OutputStream outputStream=connection.getOutputStream();
  outputStream.write("ABCD".getBytes("UTF-8"));
  outputStream.close();
  assertEquals("Page 2",readAscii(connection.getInputStream(),Integer.MAX_VALUE));
  assertEquals("POST /page1 HTTP/1.1",server.takeRequest().getRequestLine());
  RecordedRequest page2=server.takeRequest();
  assertEquals("GET /page2 HTTP/1.1",page2.getRequestLine());
  assertContainsNoneMatching(page2.getHeaders(),"Content-Length");
  assertContains(page2.getHeaders(),"Content-Type: text/plain; charset=utf-8");
  assertContains(page2.getHeaders(),"Transfer-Encoding: identity");
}
