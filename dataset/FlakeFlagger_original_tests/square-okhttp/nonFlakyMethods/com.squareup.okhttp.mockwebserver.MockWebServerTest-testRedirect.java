public void testRedirect() throws Exception {
  server.play();
  server.enqueue(new MockResponse().setResponseCode(HttpURLConnection.HTTP_MOVED_TEMP).addHeader("Location: " + server.getUrl("/new-path")).setBody("This page has moved!"));
  server.enqueue(new MockResponse().setBody("This is the new location!"));
  URLConnection connection=server.getUrl("/").openConnection();
  InputStream in=connection.getInputStream();
  BufferedReader reader=new BufferedReader(new InputStreamReader(in));
  assertEquals("This is the new location!",reader.readLine());
  RecordedRequest first=server.takeRequest();
  assertEquals("GET / HTTP/1.1",first.getRequestLine());
  RecordedRequest redirect=server.takeRequest();
  assertEquals("GET /new-path HTTP/1.1",redirect.getRequestLine());
}
